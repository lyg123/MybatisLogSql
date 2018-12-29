import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

/**
 * @author LiYiGuang
 * @date 2018/12/27 18:15
 */
public class LogUtils {
    public static void showError(String content) {
        Notifications.Bus.notify(new Notification("MybatisLogSql", "MybatisLogSql", content, NotificationType.ERROR));
    }

    public static void showInfo(String content) {
        Notifications.Bus.notify(new Notification("MybatisLogSql", "MybatisLogSql", content, NotificationType.INFORMATION));
    }
}
