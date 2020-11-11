package com.jayaprabahar.favouritezoo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jayaprabahar.favouritezoo.controller.AnimalController;

@SpringBootTest
class FavouriteZooApplicationTests {

	@Autowired
	private AnimalController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	

}
