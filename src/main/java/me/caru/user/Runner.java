package me.caru.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Runner
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 11. 20.
 */
@RefreshScope
@Component
public class Runner implements ApplicationRunner {

	@Value("${a}")
	private String a;

	@Value("${spring.profiles.active}")
	private String profile;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("a: " + a);
		System.out.println("profile: " + profile);
	}
}
