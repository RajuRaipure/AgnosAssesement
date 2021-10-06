package com.agnos.webelements;

import org.openqa.selenium.By;

public interface FlightReservationPageWebElements 
{
	By ONEWAYTRIP=By.xpath("//a/span[contains(text(),'One-way')]");
	By FROM=By.xpath("//input[@id='location-field-leg1-origin-input']/following-sibling::button");
	By SELECTORIGIN=By.xpath("//button[@data-stid='location-field-leg1-origin-result-item-button']");
	By SELECTDESTINATION=By.xpath("//button[@data-stid='location-field-leg1-destination-result-item-button']");
	By TO=By.xpath("//input[@id='location-field-leg1-destination-input']/following-sibling::button");
	By SEARCH_BUTTON=By.xpath("//button[contains(text(),'Search')]");
	By CHECKOUT=By.xpath("//button[contains(text(),'Check out')]");

}
