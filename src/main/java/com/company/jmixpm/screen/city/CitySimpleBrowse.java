package com.company.jmixpm.screen.city;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.City;

@UiController("City-simple.browse")
@UiDescriptor("city-simple-bowse.xml")
@LookupComponent("citiesTable")
public class CitySimpleBrowse extends StandardLookup<City> {
}