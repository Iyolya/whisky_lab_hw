package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findByYearEquals() {
		List<Whisky> foundWhiskys = whiskyRepository.findByYearEquals(2017);
		assertEquals("Viking Honour", foundWhiskys.get(0).getName());
	}

	@Test
	public void findDistilleryByRegion() {
		List<Distillery> foundDistilleries = distilleryRepository.findDistilleryByRegion("Islay");
		assertEquals(2, foundDistilleries.size());
	}

	@Test
	public void findWhiskyByDistilleryAndAge() {
		Distillery distilleryId1 = distilleryRepository.findDistilleryById(1L);
		List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryAndAge(distilleryId1, 15);
		assertEquals("The Glendronach Revival", foundWhiskies.get(0).getName());
	}
}


