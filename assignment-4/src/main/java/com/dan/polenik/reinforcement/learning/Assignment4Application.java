package com.dan.polenik.reinforcement.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.dan.polenik.reinforcement.learning.world.EasyGridWorld;
import com.dan.polenik.reinforcement.learning.world.HardGridWorld;
import com.dan.polenik.reinforcement.learning.world.properties.RlProperties;

/**
 * cite
 * https://github.com/juanjose49/omscs-cs7641-machine-learning-assignment-4/blob/master/src/main/java/assignment4/HardGridWorldLauncher.java
 * 
 * @author Dan
 *
 */
@EnableConfigurationProperties(value = RlProperties.class)
@SpringBootApplication
public class Assignment4Application implements CommandLineRunner {
	@Autowired(required=false)
	HardGridWorld hardGridWorld;
	@Autowired(required=false)
	EasyGridWorld easyGridWorld;
	@Autowired
	RlProperties rlProperties;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Assignment4Application.class);
		builder.headless(false).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (rlProperties.isEasyWorld()) {
			easyGridWorld.runEasyWorld();
		}
		if (rlProperties.isHardWorld()) {
			hardGridWorld.runHardWorld();

		}
	}

}
