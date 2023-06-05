package org.ytq.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ytq.dao.UserDao;
import org.ytq.domain.User;
import org.ytq.domain.query.UserQuery;

import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisplusApplicationTests {
	@Autowired
	private UserDao userDao;

	// 新增
	@Test
	void testSave(){
		User user = new User();
		user.setName("程序员");
		user.setPassword("123456");
		user.setAge(25);
		user.setTel("15545567555");
		userDao.insert(user);
	}

	// 删除 id为1665627495222026242的用户
	@Test
	void testDelete(){
		userDao.deleteById(1665627495222026242L);
	}

	// 修改
	@Test
	void testUpdate(){
		User user = new User();
		user.setId(1L);
		user.setName("666");
		user.setPassword("666");
		userDao.updateById(user);
	}

	// 根据id查询
	@Test
	void testGetById(){
		User user = userDao.selectById(2L);
		System.out.println(user);
	}


	@Test
	// 分页查询
	void testGetByPage(){
		// select * from user limit 1,2;
		// 分页拦截器
		IPage page = new Page(2, 3);
		userDao.selectPage(page, null);
		System.out.println("当前页码值: " + page.getCurrent());
		System.out.println("每页显示数: " + page.getSize());
		System.out.println("一共多少页: " + page.getPages());
		System.out.println("一共多少条数据: " + page.getTotal());
		System.out.println("数据: " + page.getRecords());
	}

	@Test
	// 按条件查询
	void testQueryByCondition(){
//		// 第一种
//		QueryWrapper queryWrapper = new QueryWrapper();
//		// 查询18岁以下的人
//		queryWrapper.lt("age", 18);
//		List<User> userList = userDao.selectList(queryWrapper);
//		System.out.println(userList);

		// 第二种, lambda表达式
//		QueryWrapper<User> queryWrapper = new QueryWrapper();
//		queryWrapper.lambda().lt(User::getAge, 18);
//		List<User> userList = userDao.selectList(queryWrapper);
//		System.out.println(userList);

		// 第三种, lambda表达式, 使用另一种接口
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		// 设置多条件，可以使用链式编程
//		lambdaQueryWrapper.lt(User::getAge, 25).gt(User::getAge, 10);
		// OR关系,例如小于10岁或者大于30岁
		lambdaQueryWrapper.lt(User::getAge, 10).or().gt(User::getAge, 30);
		List<User> userList = userDao.selectList(lambdaQueryWrapper);
		System.out.println(userList);
	}

	/**
	 * 条件查询 null
	 */
	@Test
	void testQueryByCondition2(){
		UserQuery userQuery = new UserQuery();
		userQuery.setUpper_age(30);
		userQuery.setAge(10);
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.lt(userQuery.getUpper_age() != null, User::getAge, userQuery.getUpper_age());
		lambdaQueryWrapper.gt(userQuery.getAge() != null, User::getAge, userQuery.getAge());
		List<User> userList = userDao.selectList(lambdaQueryWrapper);
		System.out.println(userList);
	}

	// 条件查询 投影
	@Test
	void testQueryByCondition3(){
//		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//		lambdaQueryWrapper.select(User::getId, User::getName, User::getAge);
//		List<User> userList = userDao.selectList(lambdaQueryWrapper);
//		System.out.println(userList);
		QueryWrapper<User> queryWrapper = new QueryWrapper();
		queryWrapper.select("count(*) as nums, age");
		queryWrapper.groupBy("age");
		List<Map<String, Object>> maps = userDao.selectMaps(queryWrapper);
		System.out.println(maps);

	}

	// 条件查询 其他
	// 等同于 select * from user where username="ytq" and password="qty";
	@Test
	void testQueryByCondition4(){
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(User::getName, "ytq1").eq(User::getPassword, "qty");
		User loginUser = userDao.selectOne(lambdaQueryWrapper);
		System.out.println(loginUser);
	}
	@Test
	void testgetAll() {
		List<User> users = userDao.selectList(null);
		System.out.println(users);
	}

}
