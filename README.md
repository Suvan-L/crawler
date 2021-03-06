# crawler 爬虫项目 (Java)
- 目前提供支持：
  - 国家企业信用信息公示系统（广东）：http://gd.gsxt.gov.cn/index.html
  - 信用中国：https://www.creditchina.gov.cn/


# 免责声明
- 一切下载及使用软件(crawler)时均被视为已经仔细阅读并完全同意以下条款： 
  - 软件(crawler)仅供个人学习与交流使用，严禁用于商业以及不良用途。 
  - 如有发现任何商业行为以及不良用途，软件(crawler)作者有权撤销使用权。 
  - 使用本软件所存在的风险将完全由其本人承担，软件(crawler)作者不承担任何责任。 
  - 软件(crawler)注明之服务条款外，其它因不当使用本软件而导致的任何意外、疏忽、合约毁坏、诽谤、版权或其他知识产权侵犯及其所造成的任何损失，本软件作者概不负责，亦不承担任何法律责任。 
  - 对于因不可抗力或因黑客攻击、通讯线路中断等不能控制的原因造成的服务中断或其他缺陷，导致用户不能正常使用，软件(pytogo)作者不承担任何责任，但将尽力减少因此给用户造成的损失或影响。 
  - 本声明未涉及的问题请参见国家有关法律法规，当本声明与国家有关法律法规冲突时，以国家法律法规为准。 
  - 本软件相关声明版权及其修改权、更新权和最终解释权均属软件(crawler)作者所有。

## 环境配置
- jdk 8.0 + 
- Mysql 8.0 +
- Maven

## 数据库配置
- 需自行修改数据库文件 src/main/resources/application-dev.yml
```
 datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
    url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT
```
- 创建 test 数据
- 执行 SQL 脚本
  - sql/3_company_info_ddl.sql
- 配置邮件服务(配置后，可调用 MailComponent 发送邮件)
```
  mail:
    host: smtp.163.com
    username: 
    password: 
    default-encoding: UTF-8

```

# 项目启动 
> 注意：启动时将会直接开启爬虫，请确保数据库链接正确
- [1] idea 直接运行 
- [2] linux 后台执行
    - `nohup java -jar crawler-1.0.0-SNAPSHOT.jar  > nohup.log &`

# 应用日志文件路径
- /root/crawler/crawler-logs
  - errorLog.log
  - runLog.log

# 文档
- doc/接口文档.md
- doc/Company-Shorter-Form.txt 【公司字典】【参考来源：https://github.com/wainshine/Company-Names-Corpus】

# 项目数据展示（开发中 ...）
- localhost:8989/index.html
