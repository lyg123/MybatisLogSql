import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LiYiGuang
 * @date 2018/12/27 11:25
 */
public class MybatisLogSql extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        String preSql = null;
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        if (clip.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
            try {
                preSql = (String) clip.getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("preSql = " + preSql);
        if (!(preSql.contains("==>  Preparing: ") &&  preSql.contains("==> Parameters: "))) {

            LogUtils.showError("Clipboard Mybatis log not contain ==>  Preparing: Or ==>  Parameters: ");
        }
        String sqlPreparing = preSql.substring(0,preSql.lastIndexOf("\n")).split("==>  Preparing: ")[1].replaceAll("\n","");
        System.out.println("sqlPreparing = " + sqlPreparing);
        String sqlParameters = preSql.split("==> Parameters: ")[1].trim();
        System.out.println("sqlParameters = " + sqlParameters);
        String[] sqlParams = sqlParameters.split(",");
        String[] colTypeArray = new String[sqlParams.length];
        String[] sqlParametersArray = new String[sqlParams.length];

        for (int i = 0; i < sqlParams.length; i++) {

            sqlParametersArray[i] = StringUtils.removeStart(sqlParams[i], " ").split("\\(")[0];
            colTypeArray[i] = StringUtils.removeStart(sqlParams[i], " ").split("\\(")[1].split("\\)")[0];
        }


        String[] str = sqlPreparing.split("\\?");

        String sqlStr = "";
        for (int index = 0; index < sqlParametersArray.length; index++) {
            String item = sqlParametersArray[index];
            if (("String".equals(colTypeArray[index]) || "Timestamp".equals(colTypeArray[index]) || "Date".equals(colTypeArray[index])) && !("null".equals(item))) {
                sqlStr = sqlStr + str[index] + "'" + item + "'";
            } else {
                sqlStr = sqlStr + str[index] + item;
            }
        }


        sqlStr = sqlStr + sqlPreparing.substring(sqlPreparing.lastIndexOf("?")+1)+";";

        System.out.println("sqlStr=" + sqlStr);

        Transferable tText = new StringSelection(sqlStr);
        clip.setContents(tText, null);
        LogUtils.showInfo("Sql already put to Clipboard");

    }



}
