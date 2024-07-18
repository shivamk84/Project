import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Trivago {

	static String firstmonth = "August 2024";
	static String firstdate = "17";
	
	static String secondmonth = "September 2024";
	static String seconddate = "21";
	
	static String Adults = "5";
	static String Childern = "4";
	static String Rooms = "3";
	
	
	// Calender
	public static void calender1(String sl, WebDriver driver, String date, String month) throws InterruptedException {
		do {
			Thread.sleep(2000);
			sl = driver.findElement(By.xpath("(//h3[@class='Heading_heading__xct3h Heading_h-s___YnjF mx-3 pb-3 font-bold'])[2]")).getText();
			driver.findElement(By.xpath("//button[@class='absolute right-0']//span[@class='Icon_wrapper__B6IoS']")).click();
		}while(!month.equals(sl));
		
		
		List<WebElement>dates = driver.findElements(By.xpath("(//div[@class='grid grid-cols-7  CalendarMonth_scrollWrapper__M6Nd6 px-5 pt-2 gap-y-1'])[1]//button[@type='button']"));
		
		for(WebElement x: dates) {
			if(x.getText().equals(date)){
				x.click();
				break;
			}
		}
	}
	
	
	// Guests and Rooms
	public static void GuestsAndRooms(String a, String Guests, WebDriver driver, int fi, int si) {
		
		if(Integer.parseInt(Guests) < Integer.parseInt(a)) {
			while(!Guests.equals(a)) {
				int b = Integer.parseInt(a);
				b--;
				a = Integer.toString(b);
				driver.findElement(By.xpath("(//button[@class='CircleButton_button__euIPd'])["+fi+"]")).click();
					
			}
		}else if(Integer.parseInt(Guests) > Integer.parseInt(a)) {
			while(!Guests.equals(a)) {
				int b = Integer.parseInt(a);
				b++;
				a = Integer.toString(b);
				driver.findElement(By.xpath("(//button[@class='CircleButton_button__euIPd'])["+si+"]")).click();
			}
		}
		
	}
	
	
	public static void main(String[] args) throws InterruptedException{
			
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("https://www.trivago.in");

		driver.findElement(By.xpath("//input[@placeholder='Where to?']")).sendKeys("bang");

		driver.findElement(By.xpath("//div[@class='flex flex-col truncate']//span[text()='Bengaluru']")).click();

		String sl = driver.findElement(By.xpath("(//h3[@class='Heading_heading__xct3h Heading_h-s___YnjF mx-3 pb-3 font-bold'])[2]")).getText();
		System.out.println(sl);
		
		calender1(sl, driver, firstdate, firstmonth);
		
		String sl2 = driver.findElement(By.xpath("(//h3[@class='Heading_heading__xct3h Heading_h-s___YnjF mx-3 pb-3 font-bold'])[2]")).getText();
		System.out.println(sl2);
		
		calender1(sl2, driver, seconddate, secondmonth);

		
		// Guests and Rooms
		
		Thread.sleep(1000);

		// Adults - 1 
		String a = driver.findElement(By.xpath("(//input[@class='NumberInput_input__1sg4W'])[1]")).getAttribute("value");
		System.out.println(a);
		GuestsAndRooms(a, Adults, driver, 1, 2);	
		
		
		// Children - 2
		String b = driver.findElement(By.xpath("(//input[@class='NumberInput_input__1sg4W'])[2]")).getAttribute("value");
		System.out.println(b);
		GuestsAndRooms(b, Childern, driver, 3, 4);
		
		// Rooms - 3
		String c = driver.findElement(By.xpath("(//input[@class='NumberInput_input__1sg4W'])[3]")).getAttribute("value");
		System.out.println(c);
		GuestsAndRooms(c, Rooms, driver, 5, 6);
		
		
		driver.findElement(By.xpath("//button[text()='Apply']")).click();
		
		driver.findElement(By.xpath("//button[@class='SearchButton_button__ldRRI SearchButton_buttonWithoutIcon__VdR_v']")).click();
		
		
		
	}

}
