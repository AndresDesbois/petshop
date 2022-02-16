package Petshop;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Petshop {

	public static void main(String[] args) throws Exception{

		System.setProperty("webdriver.chrome.driver","C:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://petstore.octoperf.com/actions/Catalog.action");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//username:andres
		//password:1234


		//--singIn--
		WebElement signInbtn = driver.findElement(By.linkText("Sign In"));
		signInbtn.click();

		//--enter username--
		WebElement enterUsername = driver.findElement(By.cssSelector("[name='username']"));
		Thread.sleep(500);
		enterUsername.sendKeys("andres");

		//--enter password--
		WebElement enterPassword = driver.findElement(By.cssSelector("[type='password']"));
		enterPassword.clear();
		Thread.sleep(250);
		enterPassword.sendKeys("1234");

		//--click on LogIn button--
		WebElement logInbtn = driver.findElement(By.cssSelector("input[name='signon']"));
		Thread.sleep(250);
		logInbtn.click();

		//TEST 1--Check if text "Sign Out" appears at the top--
		WebElement signOut = driver.findElement(By.linkText("Sign Out"));

		try {
			signOut = driver.findElement(By.linkText("Sign Out"));
			String text = signOut.getText();
			System.out.println(text+" TextLink appears at the top----TEST passed");
		}
		catch (NoSuchElementException e) {
			System.out.println("the TextLink Sign Out doesn't appear -- test failed");
			return;
		}

		//--place an order for two Items in different quantities--
		//--dogs--
		WebElement dogKind = driver.findElement(By.cssSelector("img[src='../images/sm_dogs.gif']"));
		dogKind.click();
		WebElement dogGolden = driver.findElement(By.linkText("K9-RT-01"));
		dogGolden.click();
		WebElement addToCartDog = driver.findElement(By.linkText("Add to Cart"));
		addToCartDog.click();
		//--reptiles--
		WebElement reptileKind = driver.findElement(By.cssSelector("img[src='../images/sm_reptiles.gif']"));
		reptileKind.click();
		WebElement reptileIguana = driver.findElement(By.partialLinkText("LI-02"));
		reptileIguana.click();
		WebElement addToCartRep = driver.findElement(By.linkText("Add to Cart"));
		addToCartRep.click();

		//--enter quantity for dogs--
		WebElement quanTextArea1 = driver.findElement(By.name("EST-28"));
		quanTextArea1.clear();
		Thread.sleep(500);
		quanTextArea1.sendKeys("2");

		//--enter quantity for reptiles--
		WebElement quanTextArea2 = driver.findElement(By.name("EST-13"));
		quanTextArea2.clear();
		Thread.sleep(500);
		quanTextArea2.sendKeys("3");

		//--update button amount in cart--
		WebElement updateCartbtn = driver.findElement(By.name("updateCartQuantities"));
		//	Thread.sleep(1000);
		updateCartbtn.click();

		//--subtotal order amount--
		WebElement subTotalOrder = driver.findElement(By.cssSelector("td[colspan='7']"));
		String strSubTotal = subTotalOrder.getText();
		//strSubTotal.substring(strSubTotal.lastIndexOf('$') + 1);//get only the amount part of the text which starts with the "$" sign.

		//--perform checkout-- 
		WebElement checkOutbtn = driver.findElement(By.linkText("Proceed to Checkout"));
		checkOutbtn.click();

		//--drop down credit card--
		WebElement creditcardList = driver.findElement(By.cssSelector("[name='order.cardType']"));
		Select selectCard = new Select(creditcardList);
		selectCard.selectByIndex(2);

		//--mark checkbox button--
		WebElement checkboxbtn = driver.findElement(By.cssSelector("[type='checkbox']"));
		checkboxbtn.click();
		//--continue button--
		WebElement continueBtn = driver.findElement(By.name("newOrder"));
		continueBtn.click();

		//--complete the new shipping address--as List--
		List<WebElement> inputTextList = driver.findElements(By.cssSelector("[type='text']"));
		WebElement firstName = inputTextList.get(1);
		firstName.clear();
		Thread.sleep(250);
		firstName.sendKeys("Lionel");
		WebElement lastNameText = inputTextList.get(2);
		lastNameText.clear();
		Thread.sleep(250);
		lastNameText.sendKeys("Messi");
		WebElement addres1Text = inputTextList.get(3);
		addres1Text.clear();
		Thread.sleep(250);
		addres1Text.sendKeys("Gran Via 1234");
		WebElement cityText = inputTextList.get(5);
		cityText.clear();
		Thread.sleep(250);
		cityText.sendKeys("Barcelona");
		WebElement stateText = inputTextList.get(6);
		stateText.clear();
		Thread.sleep(250);
		stateText.sendKeys("Catalunia");
		WebElement zipNumberText = inputTextList.get(7);
		zipNumberText.clear();
		Thread.sleep(250);
		zipNumberText.sendKeys("987654");
		WebElement countryText = inputTextList.get(8);
		countryText.clear();
		Thread.sleep(250);
		countryText.sendKeys("Spain");

		//--continue button--
		WebElement continueBtnAddress = driver.findElement(By.name("newOrder"));
		continueBtnAddress.click();
		//--confirm button--
		WebElement confirmOrderBtn = driver.findElement(By.cssSelector(".Button"));//using '.' for the class
		confirmOrderBtn.click();

		//--total order amount--
		WebElement totalOrder = driver.findElement(By.cssSelector("th[colspan='5']"));
		String strTotal = totalOrder.getText();
		//strTotal.substring(strTotal.lastIndexOf('$') + 1);

		//TEST 2--check if the TOTAL that appears after completing the order, matches with the SUBTOTAL in the shopping cart during the order process--
		if(strTotal.substring(strTotal.lastIndexOf('$') + 1).equals(strSubTotal.substring(strSubTotal.lastIndexOf('$') + 1))){
			System.out.println("The Total in the order matches with the Subtotal in the shopping cart-----TEST passed");
		}
		else{
			System.out.println("Total test failed");
			
		}

		//--sign out--(element signOut is in line 47)
		signOut = driver.findElement(By.linkText("Sign Out")); 
		signOut.click();

		//TEST 3--Check if text "Sign IN" appears at the top--
		try {
			signInbtn = driver.findElement(By.linkText("Sign In"));
			String text2 = signInbtn.getText();
			System.out.println(text2+" TextLink appears at the top----TEST passed");
		}
		catch (NoSuchElementException e) {
			System.out.println("Sign In test failed");
		}
		System.out.println("*****End of program*****");
		//--close browser site--
		driver.close();
	}

}
