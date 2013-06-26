package com.spd.cq.searspartsdirect.common.helpers;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SingularizerTest extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testWithWrongLocale() {
		Singularizer singularizer = Singularizer.INSTANCE;
		Exception thrown = null;
		try {
			String singular = singularizer.singularize(Locale.FRENCH,"Jeu ne c'est quois");
		} catch (Exception e) {
			thrown = e;
		}
		assertThat(thrown,is(not(nullValue())));
		assertTrue(thrown.getClass() == IllegalArgumentException.class);
	}
	
	@Test
	public void testSingularize() {
		Singularizer singularizer = Singularizer.INSTANCE;

		// Categories from http://www.searspartsdirect.com/partsdirect/product-types/ as of 26-June-2013
		assertThat(singularizer.singularize(Locale.ENGLISH, "A"), is("A"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Air cleaners"),
				is("Air cleaner"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Air compressors"),
				is("Air compressor"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Air conditioner heat pumps"), is("Air conditioner heat pump"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Air handlers"),
				is("Air handler"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Air regulator gauges"), is("Air regulator gauge"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Air-drive tools"),
				is("Air-drive tool"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Animal clippers"),
				is("Animal clipper"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Audio equipment"),
				is("Audio equipment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Auto equipment"),
				is("Auto equipment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "B"), is("B"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Back splashes"),
				is("Back splash"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Basketball equipment"), is("Basketball equipment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Baths"),
				is("Bath"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Battery chargers"),
				is("Battery charger"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Beds"), is("Bed"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Bicycles"),
				is("Bicycle"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Bird houses"),
				is("Bird house"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Blenders"),
				is("Blender"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Blood pressure meters"), is("Blood pressure meter"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Boat accessories"),
				is("Boat accessory"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Boat motors"),
				is("Boat motor"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Boats"),
				is("Boat"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Boilers"),
				is("Boiler"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Breadmakers"),
				is("Breadmaker"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Brush cutters"),
				is("Brush cutter"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Buffer polishers"),
				is("Buffer polisher"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Business system centers"), is("Business system center"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Butter dispensers"),
				is("Butter dispenser"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "C"), is("C"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Cabinets"),
				is("Cabinet"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Calculators"),
				is("Calculator"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Camcorders"),
				is("Camcorder"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Cameras"),
				is("Camera"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Camping equipment"),
				is("Camping equipment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Can openers"),
				is("Can opener"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Canner cookers"),
				is("Canner cooker"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Car carriers"),
				is("Car carrier"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Carburetor services"),
				is("Carburetor service"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Cardiofits"),
				is("Cardiofit"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Carpet floor sweepers"), is("Carpet floor sweeper"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Caster sets"),
				is("Caster set"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Cement mixers"),
				is("Cement mixer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Chainsaws"),
				is("Chainsaw"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Chargers"),
				is("Charger"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Chipper shredders"),
				is("Chipper shredder"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Choppers"),
				is("Chopper"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Clipsticks"),
				is("Clipstick"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Coffee grinders"),
				is("Coffee grinder"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Coffee makers"),
				is("Coffee maker"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Coin mechanisms"),
				is("Coin mechanism"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Compactors"),
				is("Compactor"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Computers"),
				is("Computer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Cooktops"),
				is("Cooktop"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Cookware"),
				is("Cookware"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Copiers"),
				is("Copier"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Cosmetic hutches"),
				is("Cosmetic hutch"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Crepe makers"),
				is("Crepe maker"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Cultivators"),
				is("Cultivator"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "D"), is("D"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Deep fryers"),
				is("Deep fryer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Dehumidifiers"),
				is("Dehumidifier"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Dehydrators"),
				is("Dehydrator"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Dishwashers"),
				is("Dishwasher"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Disk drives"),
				is("Disk drive"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Door canopies"),
				is("Door canopy"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Drain augers"),
				is("Drain auger"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Drawer units"),
				is("Drawer unit"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Drills"),
				is("Drill"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Dryers"),
				is("Dryer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Dust busters"),
				is("Dust buster"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "E"), is("E"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Edge crafters"),
				is("Edge crafter"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Edgers"),
				is("Edger"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Electric knives"),
				is("Electric knife"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Electric skillets"),
				is("Electric skillet"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Ellipticals"),
				is("Elliptical"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Engraving tools"),
				is("Engraving tool"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Evaporative coils"),
				is("Evaporative coil"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Evaporative coolers"),
				is("Evaporative cooler"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Exercise cycles"),
				is("Exercise cycle"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Exercise steppers"),
				is("Exercise stepper"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "F"), is("F"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Fans"), is("Fan"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Faucets"),
				is("Faucet"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Fax machines"),
				is("Fax machine"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Fireplace equipment"),
				is("Fireplace equipment"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Fishing equipment"),
				is("Fishing equipment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Flashlights"),
				is("Flashlight"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Flexaloungers"),
				is("Flexalounger"));
		// No way we can handle this one - pretty sure it is actually incorrect
		// as is. Need the oracle singular form for it. If it had a slash, Compound would handle it
		// assertThat(singularizer.singularize(Locale.ENGLISH,
		// "Floor scrubbers polishers"), is(""));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Floor scrubbers / polishers"), is("Floor scrubber / polisher"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Food processors"),
				is("Food processor"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Food steamers"),
				is("Food steamer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Fountains"),
				is("Fountain"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Freezers"),
				is("Freezer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Fry pans"),
				is("Fry pan"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Furnaces"),
				is("Furnace"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "G"), is("G"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Gallery rails"),
				is("Gallery rail"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Game tables"),
				is("Game table"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Garage door low-head kits"), is("Garage door low-head kit"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Garage door openers"),
				is("Garage door opener"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Garage doors"),
				is("Garage door"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Garbage disposals"),
				is("Garbage disposal"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Gas engine starters"),
				is("Gas engine starter"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Generators"),
				is("Generator"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Glue guns"),
				is("Glue gun"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Go karts & minibikes"), is("Go kart & minibike"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Golf equipment"),
				is("Golf equipment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Grass catchers"),
				is("Grass catcher"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Grass line trimmers"),
				is("Grass line trimmer"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Greasing equipment"),
				is("Greasing equipment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Griddles"),
				is("Griddle"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Grills"),
				is("Grill"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Grills & smokers"),
				is("Grill & smoker"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Grinders"),
				is("Grinder"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Guns"), is("Gun"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "H"), is("H"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Hammer guns"),
				is("Hammer gun"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Hand trucks"),
				is("Hand truck"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Hearing aids"),
				is("Hearing aid"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Heat guns"),
				is("Heat gun"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Heaters"),
				is("Heater"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Heating cooling package units"),
				is("Heating cooling package unit"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Hedge trimmers"),
				is("Hedge trimmer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Hitches"),
				is("Hitch"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Hobby tools"),
				is("Hobby tool"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Hoists"),
				is("Hoist"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Hole diggers"),
				is("Hole digger"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Home generators"),
				is("Home generator"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Hose reels"),
				is("Hose reel"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Hot plates"),
				is("Hot plate"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Hot water dispensers"), is("Hot water dispenser"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Humidifiers"),
				is("Humidifier"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Hydraulic jacks"),
				is("Hydraulic jack"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "I"), is("I"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Ice cream makers"),
				is("Ice cream maker"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Ice makers"),
				is("Ice maker"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Irons"),
				is("Iron"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "J"), is("J"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Jointer planers"),
				is("Jointer planer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Juicers"),
				is("Juicer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "K"), is("K"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Kitchen counter-top appliances"),
				is("Kitchen counter-top appliance"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Kitchen equipment"),
				is("Kitchen equipment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "L"), is("L"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Labeling systems"),
				is("Labeling system"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Laminate trimmers"),
				is("Laminate trimmer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Lantern posts"),
				is("Lantern post"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Lanterns"),
				is("Lantern"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Lathes"),
				is("Lathe"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Laundry tubs"),
				is("Laundry tub"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Lawn sprinklers"),
				is("Lawn sprinkler"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Lawn sweeper vacuums"), is("Lawn sweeper vacuum"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Lawn water systems"),
				is("Lawn water system"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Lawnmower power reels"), is("Lawnmower power reel"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Leaf blowers"),
				is("Leaf blower"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Lighting fixtures"),
				is("Lighting fixture"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Lights"),
				is("Light"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Log splitters"),
				is("Log splitter"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Lotion dispensers"),
				is("Lotion dispenser"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "M"), is("M"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Massage products"),
				is("Massage product"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Measuring tools"),
				is("Measuring tool"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Meat choppers & slicers"), is("Meat chopper & slicer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Metal detectors"),
				is("Metal detector"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Microwave melter dispensers"),
				is("Microwave melter dispenser"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Microwaves"),
				is("Microwave"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Mixers"),
				is("Mixer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Modems"),
				is("Modem"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Molding head sets"),
				is("Molding head set"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Mopeds"),
				is("Moped"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Mortisers"),
				is("Mortiser"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Moto-tools"),
				is("Moto-tool"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Motor cars"),
				is("Motor car"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Motorcycles"),
				is("Motorcycle"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "N"), is("N"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Nailers"),
				is("Nailer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "O"), is("O"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Outdoor lamps"),
				is("Outdoor lamp"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "P"), is("P"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Painting equipment"),
				is("Painting equipment"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Panel decorators"),
				is("Panel decorator"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Panel lifts"),
				is("Panel lift"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Pasta makers"),
				is("Pasta maker"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Patio canopies"),
				is("Patio canopy"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Patio covers"),
				is("Patio cover"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Personal valet systems"), is("Personal valet system"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Pest control"),
				is("Pest control"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Pipe cutters"),
				is("Pipe cutter"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Planers"),
				is("Planer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Plate joiners"),
				is("Plate joiner"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Playground equipment"), is("Playground equipment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Polishers"),
				is("Polisher"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Popcorn poppers"),
				is("Popcorn popper"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Porch curtains"),
				is("Porch curtain"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Portable broilers"),
				is("Portable broiler"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Portable convection ovens"), is("Portable convection oven"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Portable oven broilers"), is("Portable oven broiler"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Portable ovens"),
				is("Portable oven"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Portable rotisseries"), is("Portable rotisserie"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Potpourri pots"),
				is("Potpourri pot"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Poultry farm equipment"), is("Poultry farm equipment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Poultry nests"),
				is("Poultry nest"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Power hammers"),
				is("Power hammer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Power rakes"),
				is("Power rake"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Power steamers"),
				is("Power steamer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Power washers"),
				is("Power washer"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Pressure cookers"),
				is("Pressure cooker"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Printers"),
				is("Printer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Projectors"),
				is("Projector"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Pruners"),
				is("Pruner"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Pumps"),
				is("Pump"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "R"), is("R"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "RV appliances"),
				is("RV appliance"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Radio citizen bands"),
				is("Radio citizen band"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Radio intercoms"),
				is("Radio intercom"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Range hoods"),
				is("Range hood"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Ranges"),
				is("Range"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Ratchet/nut drivers"),
				is("Ratchet/nut driver"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Refrigerators"),
				is("Refrigerator"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Riding mowers & tractors"), is("Riding mower & tractor"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Riding toys"),
				is("Riding toy"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Riveters"),
				is("Riveter"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Room air conditioners"), is("Room air conditioner"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Rotary tools"),
				is("Rotary tool"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Routers"),
				is("Router"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Rowers"),
				is("Rower"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "S"), is("S"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Sanders"),
				is("Sander"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Saunas"),
				is("Sauna"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Saws"), is("Saw"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Scooters"),
				is("Scooter"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Screen enclosures"),
				is("Screen enclosure"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Screw drivers"),
				is("Screw driver"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Security systems"),
				is("Security system"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Seeders"),
				is("Seeder"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Sewing machines"),
				is("Sewing machine"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Sharpeners"),
				is("Sharpener"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Shavers"),
				is("Shaver"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Shear attachments"),
				is("Shear attachment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Shelf units"),
				is("Shelf unit"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Shop presses"),
				is("Shop press"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Showers"),
				is("Shower"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Sinks"),
				is("Sink"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Ski equipment"),
				is("Ski equipment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Skiing machines"),
				is("Skiing machine"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Slow cookers"),
				is("Slow cooker"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Snow removal equipment"), is("Snow removal equipment"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Snowmobile trailers"),
				is("Snowmobile trailer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Soda fountains"),
				is("Soda fountain"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Spas"), is("Spa"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Spreaders"),
				is("Spreader"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Staplers"),
				is("Stapler"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Storm doors"),
				is("Storm door"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Sun lamps"),
				is("Sun lamp"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Swimming pools"),
				is("Swimming pool"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "T"), is("T"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Tanning equipment"),
				is("Tanning equipment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Telephones"),
				is("Telephone"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Televisions"),
				is("Television"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Tiller accessories"),
				is("Tiller accessory"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Toaster ovens"),
				is("Toaster oven"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Toasters"),
				is("Toaster"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Toilets"),
				is("Toilet"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Tool storage"),
				is("Tool storage"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Toy scooters"),
				is("Toy scooter"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Toys"), is("Toy"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Trailers"),
				is("Trailer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Trampolines"),
				is("Trampoline"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Treadmills"),
				is("Treadmill"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Trimmers"),
				is("Trimmer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Tripods"),
				is("Tripod"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Typewriters & word processors"),
				is("Typewriter & word processor"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "U"), is("U"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "UV lights"),
				is("UV light"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Unicycles"),
				is("Unicycle"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Utility carts"),
				is("Utility cart"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "V"), is("V"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Vacuums"),
				is("Vacuum"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Vent systems"),
				is("Vent system"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Venting dryers"),
				is("Venting dryer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Video equipment"),
				is("Video equipment"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Vises"),
				is("Vise"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "W"), is("W"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Waffle makers"),
				is("Waffle maker"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Walk-behind lawn mowers"), is("Walk-behind lawn mower"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Wall ovens"),
				is("Wall oven"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Wardrobe doors/mirrors"), is("Wardrobe door/mirror"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Warming drawers"),
				is("Warming drawer"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Washer dryer combos"),
				is("Washer dryer combo"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Washers"),
				is("Washer"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Water Treatment equipment"), is("Water Treatment equipment"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Water dispensers"),
				is("Water dispenser"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Water heaters"),
				is("Water heater"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Water pressure reducing valves"),
				is("Water pressure reducing valve"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Water softeners"),
				is("Water softener"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Waxing systems"),
				is("Waxing system"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Weight systems"),
				is("Weight system"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Welders"),
				is("Welder"));
		assertThat(
				singularizer.singularize(Locale.ENGLISH, "Wet carpet cleaners"),
				is("Wet carpet cleaner"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Wheelbarrows"),
				is("Wheelbarrow"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Wheelchairs & scooters"), is("Wheelchair & scooter"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Winches"),
				is("Winch"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Windmills"),
				is("Windmill"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Window awnings"),
				is("Window awning"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Wine & beverage coolers"), is("Wine & beverage cooler"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Woks"), is("Wok"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Wood stoves"),
				is("Wood stove"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Work lights"),
				is("Work light"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Workbenches"),
				is("Workbench"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Wrenches"),
				is("Wrench"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Y"), is("Y"));
		assertThat(singularizer.singularize(Locale.ENGLISH,
				"Yard and farm buildings"), is("Yard and farm building"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Yard carts"),
				is("Yard cart"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Yard sprayers"),
				is("Yard sprayer"));
		assertThat(singularizer.singularize(Locale.ENGLISH, "Yogurt makers"),
				is("Yogurt maker"));

		// Categories from David's Skype message ~12:44pm 26-June-2013
		assertThat(singularizer.singularize(Locale.ENGLISH, "chainsaws, dishwashers, microwaves, ranges, refrigerators"),
				is("chainsaw, dishwasher, microwave, range, refrigerator"));
	}
}
