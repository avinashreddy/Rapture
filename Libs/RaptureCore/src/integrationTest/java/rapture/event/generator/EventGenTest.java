/**
 * Copyright (C) 2011-2015 Incapture Technologies LLC
 *
 * This is an autogenerated license statement. When copyright notices appear below
 * this one that copyright supercedes this statement.
 *
 * Unless required by applicable law or agreed to in writing, software is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 *
 * Unless explicit permission obtained in writing this software cannot be distributed.
 */
package rapture.event.generator;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class EventGenTest {
	@Test
	public void testEventGeneration() {
		DateTime c = new DateTime();
		for(int i=0; i< 100; i++) {
			List<String> events = EventGenerator.generateEventNames(c, true);
			printOutResults(c, events);
			c = c.plusMinutes(1);
		}
	}
	
	private static DateTimeFormatter df = DateTimeFormat.fullDateTime();
	private void printOutResults(DateTime dt, List<String> events) {
		System.out.println("For : " + df.print(dt));
		for(String e : events) {
			System.out.println(e);
		}
	}
}
