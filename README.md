1.springboot1
1版本
2.springboot2
2版本
编写实体类com.zhg.demo.mybatis.entity.User
public class User implements Serializable {
    private Long id;//编号
    private String username;//用户名
    private String password;//密码
    //省略get set方法
}
编写接口com.zhg.demo.mybatis.mapper.UserMapper
注意：需要使用@Mapper注解，不然SpringBoot无法扫描
@Mapper//指定这是一个操作数据库的mapper
public interface UserMapper {
    List<User> findAll();
}
编写在resources文件中创建 mapper/UserMapper.xml文件
注意：
1.namespace中需要与使用@Mapper的接口对应
2.UserMapper.xml文件名称必须与使用@Mapper的接口一致
3.标签中的id必须与@Mapper的接口中的方法名一致，且参数一致
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.zhg.demo.mybatis.mapper.UserMapper">
    <select id="findAll" resultType="User">
        SELECT * FROM tb_user
    </select>
</mapper>
编写接口com.zhg.demo.mybatis.service
public interface UserService {
    List<User> findAll();
}
编写实现类com.zhg.demo.mybatis.service.impl.UserServiceimpl
注意：需要在接口实现类中使用@Service注解，才能被SpringBoot扫描，在Controller中使用@Authwired注入
@Service("userService")
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
编写api接口com.zhg.demo.mybatis.controller.UserController
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

}
在启动类中添加对@MapperScan的扫描
@SpringBootApplication
@MapperScan("com.zhg.demo.mybatis.mapper")//使用MapperScan批量扫描所有的Mapper接口；
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
四、配置文件
注意：
1.mybatis中的mapper-locations是mapper的xml文件位置
2.mybatis中的type-aliases-package是为了配置xml文件中resultType返回值的包位置，如果未配置请使用全包名如下：

<select id="findAll" resultType="com.zhg.demo.mybatis.entity.User">
        SELECT * FROM tb_user
</select>
在resources中创建application.yml文件，并编写配置
server:
  port: 8081
spring:
  #数据库连接配置
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://47.107.105.158:3306/test?characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456

#mybatis的相关配置
mybatis:
  #mapper配置文件
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.zhg.demo.mybatis.entity
  #开启驼峰命名
  configuration:
    map-underscore-to-camel-case: true
五、创建数据库和数据表
-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'laowang', '112233');

————————————————
版权声明：本文为CSDN博主「hello_world!」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/djrm11/article/details/104779289