package org.ytq.mybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ytq.dao.UserDao;
import org.ytq.domain.User;

import java.util.List;

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
	void testgetAll() {
		List<User> users = userDao.selectList(null);
		System.out.println(users);
	}

}
