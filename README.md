# MybatisLogSql
IDEA Plugin MyBatis Log Sql.       from Clipboard mybatis log,By this Plugin Process  create result sql will put to Clipboard.
<br/>
      ##English
      Restore the mybatis generate sql to original whole sql.<br/>
      It will generate sql statements with replace ? to the really param value.<br/>
      Through the "Tools -> MyBatis Log Plugin" menu you can tail the sql log.<br/>
      You can selected the "Filter" button on the left to filter the contents don't wanna display.<br/>
      You can selected the "Format Sql" button on the left to format the generate sql statements.<br/>
      You can select the console sql log and right click "Restore Sql from Selection" menu to restore sql.<br/>
      Prerequisite: sql log must contain "Preparing:" and "Parameters:" <br/>
      ##中文
把 mybatis 输出的sql日志还原成完整的sql语句<br/>
      将日志输出的sql语句中的问号 ? 替换成真正的参数值<br/>
      通过 "右键鼠标，菜单->MybatisLogSql" 菜单<br/>
      把Mybatis log复制到剪贴板，然后鼠标右击菜单MybatisLogSql，这样sql语句结果就复制到剪贴板<br/>
      注意：剪贴板sql日志须包含"==>  Preparing:"和"==> Parameters:"才能正常解析<br/>
      Example: Clipboard mybatis log
==>  Preparing: SELECT * from t_test where id = ?
12-28 17:22:05.102 [qtp1635985705-26] DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:159) - ==> Parameters: 1(Long)
      <br/>
      <a href="https://github.com/lyg123/MybatisLogSql">Github</a> | <a href="https://github.com/lyg123/MybatisLogSql/issues">Issues</a><br/>
      <br/>
