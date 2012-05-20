/*
 *  This File is licensed under GPL v3.
 *  Copyright (C) 2012 Rene Peinthor.
 *
 *  This file is part of TrafficChecker.
 *
 *  BlueMouse is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  TrafficChecker is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with TrafficChecker.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.oldsch00l.TrafficChecker;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

public class GermanyRegions {
	public static boolean pointInPolygon(GeoPoint point, List<GeoPoint> polygonList) {
//		int pnpoly(int nvert, float *vertx, float *verty, float testx, float testy)
//		{
//		  int i, j, c = 0;
//		  for (i = 0, j = nvert-1; i < nvert; j = i++) {
//		    if ( ((verty[i]>testy) != (verty[j]>testy)) &&
//			 (testx < (vertx[j]-vertx[i]) * (testy-verty[i]) / (verty[j]-verty[i]) + vertx[i]) )
//		       c = !c;
//		  }
//		  return c;
//		}
		boolean bFound = false;
		int i, j;
		for( i = 0, j = polygonList.size() - 1; i < polygonList.size(); j = i++) {
			GeoPoint polyP1 = polygonList.get(i);
			GeoPoint polyP2 = polygonList.get(j);
			if( ((polyP1.getLongitudeE6() > point.getLongitudeE6()) != (polyP2.getLongitudeE6() > point.getLongitudeE6()) )
					&&	(point.getLatitudeE6() < (polyP2.getLatitudeE6() - polyP1.getLatitudeE6())
					*	(point.getLongitudeE6() - polyP1.getLongitudeE6())
					/	(polyP2.getLongitudeE6() - polyP1.getLongitudeE6()) + polyP1.getLatitudeE6() ) )
				bFound = !bFound;
		}
		return bFound;
	}
	
	public static List<GeoPoint> createBerlinCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(52379000,13130000) );
		retList.add( new GeoPoint(52332000,13638000) );
		retList.add( new GeoPoint(52450200,13793000) );
		retList.add( new GeoPoint(52490300,13652000) );
		retList.add( new GeoPoint(52547100,13699000) );
		retList.add( new GeoPoint(52685500,13483000) );
		retList.add( new GeoPoint(52674700,13274000) );
		retList.add( new GeoPoint(52593900,13118000) );
		retList.add( new GeoPoint(52419200,13079000) );
		retList.add( new GeoPoint(52379000,13130000) );
		return retList;
	}
	
	public static List<GeoPoint> createHamburgCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(53559100, 9720000) );
		retList.add( new GeoPoint(53467600, 9792000) );
		retList.add( new GeoPoint(53396400, 9928000) );
		retList.add( new GeoPoint(53390700,10239000) );
		retList.add( new GeoPoint(53447200,10341000) );
		retList.add( new GeoPoint(53523200,10221000) );
		retList.add( new GeoPoint(53635700,10234000) );
		retList.add( new GeoPoint(53747900,10191000) );
		retList.add( new GeoPoint(53730800,10079000) );
		retList.add( new GeoPoint(53658500, 9907000) );
		retList.add( new GeoPoint(53636500, 9763000) );
		retList.add( new GeoPoint(53559100, 9720000) );
		return retList;
	}
	
	public static List<GeoPoint> createBremenCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(53029696,8735913));
		retList.add( new GeoPoint(53041989,8767234));
		retList.add( new GeoPoint(53021408,8813015));
		retList.add( new GeoPoint(53012173,8851459));
		retList.add( new GeoPoint(53022394,8874622));
		retList.add( new GeoPoint(53011526,8890593));
		retList.add( new GeoPoint(53007389,8927334));
		retList.add( new GeoPoint(53029874,8955329));
		retList.add( new GeoPoint(53048349,8989969));
		retList.add( new GeoPoint(53060049,8971972));
		retList.add( new GeoPoint(53085338,8969870));
		retList.add( new GeoPoint(53097880,8999431));
		retList.add( new GeoPoint(53103768,8968251));
		retList.add( new GeoPoint(53114429,8959430));
		retList.add( new GeoPoint(53126766,8991737));
		retList.add( new GeoPoint(53157149,8942714));
		retList.add( new GeoPoint(53140189,8911924));
		retList.add( new GeoPoint(53135007,8868393));
		retList.add( new GeoPoint(53167492,8832768));
		retList.add( new GeoPoint(53164739,8769748));
		retList.add( new GeoPoint(53187232,8708932));
		retList.add( new GeoPoint(53180248,8673065));
		retList.add( new GeoPoint(53200482,8628902));
		retList.add( new GeoPoint(53190120,8595897));
		retList.add( new GeoPoint(53197497,8590711));
		retList.add( new GeoPoint(53214212,8605523));
		retList.add( new GeoPoint(53220867,8576498));
		retList.add( new GeoPoint(53212445,8555287));
		retList.add( new GeoPoint(53231994,8518262));
		retList.add( new GeoPoint(53227414,8476092));
		retList.add( new GeoPoint(53183799,8521378));
		retList.add( new GeoPoint(53164523,8613490));
		retList.add( new GeoPoint(53115626,8645047));
		retList.add( new GeoPoint(53083500,8670029));
		retList.add( new GeoPoint(53079121,8699882));
		retList.add( new GeoPoint(53042370,8704214));
		retList.add( new GeoPoint(53029696,8735913));
		
		
		retList.add( new GeoPoint(53508236,8557311));
		retList.add( new GeoPoint(53498860,8571123));
		retList.add( new GeoPoint(53489218,8563142));
		retList.add( new GeoPoint(53482278,8584070));
		retList.add( new GeoPoint(53482549,8612479));
		retList.add( new GeoPoint(53515051,8659780));
		retList.add( new GeoPoint(53554157,8647168));
		retList.add( new GeoPoint(53573833,8623869));
		retList.add( new GeoPoint(53608536,8666553));
		retList.add( new GeoPoint(53614338,8642636));
		retList.add( new GeoPoint(53599030,8594016));
		retList.add( new GeoPoint(53619104,8485379));
		retList.add( new GeoPoint(53536901,8562775));
		retList.add( new GeoPoint(53508236,8557311));
		return retList;
	}
	
	public static List<GeoPoint> createBayernCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(47244750, 10211520) );
		retList.add( new GeoPoint(47443840, 11558658) );
		retList.add( new GeoPoint(47449392, 13059792) );
		retList.add( new GeoPoint(48733006, 13932485) );
		retList.add( new GeoPoint(50273629, 12366740) );
		retList.add( new GeoPoint(50600344, 11425447) );
		retList.add( new GeoPoint(50427546, 10733006) );
		retList.add( new GeoPoint(50588152, 10103087) );
		retList.add( new GeoPoint(50109392, 8942108) );
		retList.add( new GeoPoint(49812859, 9008764) );
		retList.add( new GeoPoint(49503722, 9045369) );
		retList.add( new GeoPoint(49683002, 9565414) );
		retList.add( new GeoPoint(49423165, 9945932) );
		retList.add( new GeoPoint(48867543, 10346680) );
		retList.add( new GeoPoint(48396784, 9920461) );
		retList.add( new GeoPoint(47742412, 10000290) );
		retList.add( new GeoPoint(47553133, 9407384) );
		retList.add( new GeoPoint(47244750, 10211520) );
		return retList;
	}
	
	public static List<GeoPoint> createBadenWuerttembergCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(48080356,10174255));
		retList.add( new GeoPoint(48395851,10030855));
		retList.add( new GeoPoint(48520216,10333994));
		retList.add( new GeoPoint(48619779,10340478));
		retList.add( new GeoPoint(48676661,10505185));
		retList.add( new GeoPoint(48961466,10469320));
		retList.add( new GeoPoint(49106573,10280070));
		retList.add( new GeoPoint(49269031,10198792));
		retList.add( new GeoPoint(49552779,10132106));
		retList.add( new GeoPoint(49808121,9685468));
		retList.add( new GeoPoint(49792318,9306957));
		retList.add( new GeoPoint(49636682,9266730));
		retList.add( new GeoPoint(49593570,9087543));
		retList.add( new GeoPoint(49511994,8942502));
		retList.add( new GeoPoint(49544483,8752887));
		retList.add( new GeoPoint(49634293,8697348));
		retList.add( new GeoPoint(49582797,8392350));
		retList.add( new GeoPoint(49300457,8438053));
		retList.add( new GeoPoint(48621615,7790477));
		retList.add( new GeoPoint(47776451,7424081));
		retList.add( new GeoPoint(47521995,7620461));
		retList.add( new GeoPoint(47523053,8169267));
		retList.add( new GeoPoint(47582032,8598592));
		retList.add( new GeoPoint(47662963,8690522));
		retList.add( new GeoPoint(47631106,9064504));
		retList.add( new GeoPoint(47523187,9503087));
		retList.add( new GeoPoint(47622969,9862498));
		retList.add( new GeoPoint(47621865,10136739));
		retList.add( new GeoPoint(48080356,10174255));
		return retList;
	}
	
	public static List<GeoPoint> createSaarlandCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(49117438,7415073));
		retList.add( new GeoPoint(49230110,7347647));
		retList.add( new GeoPoint(49353688,7464366));
		retList.add( new GeoPoint(49422757,7309061));
		retList.add( new GeoPoint(49487232,7332270));
		retList.add( new GeoPoint(49605908,7282458));
		retList.add( new GeoPoint(49662854,7016909));
		retList.add( new GeoPoint(49547778,6614588));
		retList.add( new GeoPoint(49564713,6364944));
		retList.add( new GeoPoint(49450682,6343115));
		retList.add( new GeoPoint(49430765,6507243));
		retList.add( new GeoPoint(49227584,6623832));
		retList.add( new GeoPoint(49122581,6775977));
		retList.add( new GeoPoint(49183332,6917389));
		retList.add( new GeoPoint(49169892,6989631));
		retList.add( new GeoPoint(49081399,7061370));
		retList.add( new GeoPoint(49117438,7415073));
		return retList;
	}
	
	public static List<GeoPoint> createRheinlandPfalzCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(48932146,8271876));
		retList.add( new GeoPoint(49439204,8589801));
		retList.add( new GeoPoint(49685283,8402147));
		retList.add( new GeoPoint(49755485,8522211));
		retList.add( new GeoPoint(50072944,8216444));
		retList.add( new GeoPoint(49994556,7953515));
		retList.add( new GeoPoint(50053277,7872113));
		retList.add( new GeoPoint(50278580,8159367));
		retList.add( new GeoPoint(50501360,8034453));
		retList.add( new GeoPoint(50547272,8190019));
		retList.add( new GeoPoint(50698491,8142241));
		retList.add( new GeoPoint(50969360,7821431));
		retList.add( new GeoPoint(50909430,7708286));
		retList.add( new GeoPoint(50799539,7604078));
		retList.add( new GeoPoint(50668814,7197324));
		retList.add( new GeoPoint(50479227,6710614));
		retList.add( new GeoPoint(50393218,6708908));
		retList.add( new GeoPoint(50400150,6360701));
		retList.add( new GeoPoint(50154257,6082817));
		retList.add( new GeoPoint(49908563,6135205));
		retList.add( new GeoPoint(49774340,6458752));
		retList.add( new GeoPoint(49549802,6335721));
		retList.add( new GeoPoint(49494556,6627527));
		retList.add( new GeoPoint(49609518,7013589));
		retList.add( new GeoPoint(49533301,7247501));
		retList.add( new GeoPoint(49429301,7224662));
		retList.add( new GeoPoint(49327242,7344931));
		retList.add( new GeoPoint(49196813,7250854));
		retList.add( new GeoPoint(49037701,7617407));
		retList.add( new GeoPoint(48932146,8271876));
		return retList;
	}
	
	public static List<GeoPoint> createHessenCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(49498537,9139399));
		retList.add( new GeoPoint(49747811,9172992));
		retList.add( new GeoPoint(50075516,9051795));
		retList.add( new GeoPoint(50067692,9493597));
		retList.add( new GeoPoint(50347408,9824517));
		retList.add( new GeoPoint(50393167,9990440));
		retList.add( new GeoPoint(50636963,10143728));
		retList.add( new GeoPoint(50713707,9979779));
		retList.add( new GeoPoint(50942650,10113357));
		retList.add( new GeoPoint(51012717,10242364));
		retList.add( new GeoPoint(51197302,10279600));
		retList.add( new GeoPoint(51315912,10007436));
		retList.add( new GeoPoint(51438300,9931491));
		retList.add( new GeoPoint(51390963,9673299));
		retList.add( new GeoPoint(51615180,9744274));
		retList.add( new GeoPoint(51674856,9494710));
		retList.add( new GeoPoint(51495428,8857468));
		retList.add( new GeoPoint(51410944,8896659));
		retList.add( new GeoPoint(51426309,8721552));
		retList.add( new GeoPoint(51258586,8512996));
		retList.add( new GeoPoint(51188456,8714992));
		retList.add( new GeoPoint(51102188,8492904));
		retList.add( new GeoPoint(51013693,8478551));
		retList.add( new GeoPoint(50795782,8102073));
		retList.add( new GeoPoint(50586361,8047593));
		retList.add( new GeoPoint(50451802,7918010));
		retList.add( new GeoPoint(50307121,8047866));
		retList.add( new GeoPoint(50084456,7735888));
		retList.add( new GeoPoint(49948263,7880940));
		retList.add( new GeoPoint(49995477,8230364));
		retList.add( new GeoPoint(49721536,8285621));
		retList.add( new GeoPoint(49503423,8497977));
		retList.add( new GeoPoint(49506273,8730978));
		retList.add( new GeoPoint(49363025,8814026));
		retList.add( new GeoPoint(49498537,9139399));
		return retList;
	}
	
	public static List<GeoPoint> createThueringenCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(50626341,9844612));
		retList.add( new GeoPoint(50617539,10005248));
		retList.add( new GeoPoint(50495825,10015665));
		retList.add( new GeoPoint(50524726,10171678));
		retList.add( new GeoPoint(50310020,10532045));
		retList.add( new GeoPoint(50216340,10591381));
		retList.add( new GeoPoint(50179081,10739824));
		retList.add( new GeoPoint(50252445,10879092));
		retList.add( new GeoPoint(50357389,10774932));
		retList.add( new GeoPoint(50331183,11101245));
		retList.add( new GeoPoint(50249145,11159329));
		retList.add( new GeoPoint(50253163,11289112));
		retList.add( new GeoPoint(50448183,11318368));
		retList.add( new GeoPoint(50484305,11390750));
		retList.add( new GeoPoint(50356304,11500859));
		retList.add( new GeoPoint(50399912,11918846));
		retList.add( new GeoPoint(50498581,11992308));
		retList.add( new GeoPoint(50544459,11921523));
		retList.add( new GeoPoint(50587267,11979185));
		retList.add( new GeoPoint(50517465,12049037));
		retList.add( new GeoPoint(50665910,12357608));
		retList.add( new GeoPoint(50757603,12277301));
		retList.add( new GeoPoint(50912612,12670713));
		retList.add( new GeoPoint(51088247,12549166));
		retList.add( new GeoPoint(51111431,12260192));
		retList.add( new GeoPoint(50985390,12216861));
		retList.add( new GeoPoint(51072537,11892555));
		retList.add( new GeoPoint(51140282,11518973));
		retList.add( new GeoPoint(51239340,11459562));
		retList.add( new GeoPoint(51298295,11515179));
		retList.add( new GeoPoint(51421263,11360041));
		retList.add( new GeoPoint(51445839,11011722));
		retList.add( new GeoPoint(51621552,10956324));
		retList.add( new GeoPoint(51669316,10669389));
		retList.add( new GeoPoint(51574933,10579358));
		retList.add( new GeoPoint(51612373,10368384));
		retList.add( new GeoPoint(51378081,9906390));
		retList.add( new GeoPoint(51271639,9936696));
		retList.add( new GeoPoint(51185453,10161652));
		retList.add( new GeoPoint(51146885,10084038));
		retList.add( new GeoPoint(50948246,9926178));
		retList.add( new GeoPoint(50882479,9954660));
		retList.add( new GeoPoint(50626341,9844612));
		return retList;
	}
	
	public static List<GeoPoint> createSachsenCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(50467448,11877073));
		retList.add( new GeoPoint(50340459,11957894));
		retList.add( new GeoPoint(50151770,12324160));
		retList.add( new GeoPoint(50817956,14840086));
		retList.add( new GeoPoint(51379391,15171272));
		retList.add( new GeoPoint(51611035,14705916));
		retList.add( new GeoPoint(51547548,14072861));
		retList.add( new GeoPoint(51419752,13973928));
		retList.add( new GeoPoint(51381855,13644161));
		retList.add( new GeoPoint(51465420,13396132));
		retList.add( new GeoPoint(51410306,13250077));
		retList.add( new GeoPoint(51594489,13198999));
		retList.add( new GeoPoint(51681334,13074016));
		retList.add( new GeoPoint(51700765,12778861));
		retList.add( new GeoPoint(51545060,12132816));
		retList.add( new GeoPoint(51132024,12136840));
		retList.add( new GeoPoint(50948735,12611795));
		retList.add( new GeoPoint(50815126,12217359));
		retList.add( new GeoPoint(50739011,12205034));
		retList.add( new GeoPoint(50667636,12271301));
		retList.add( new GeoPoint(50609820,12065987));
		retList.add( new GeoPoint(50651492,12006540));
		retList.add( new GeoPoint(50555523,11839471));
		retList.add( new GeoPoint(50467448,11877073));
		return retList;
	}
	
	public static List<GeoPoint> createNordrheinWestfalenCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(50309047,6375108));
		retList.add( new GeoPoint(50333512,6814826));
		retList.add( new GeoPoint(50440984,6882374));
		retList.add( new GeoPoint(50752281,7686915));
		retList.add( new GeoPoint(50877717,7805068));
		retList.add( new GeoPoint(50644887,8089431));
		retList.add( new GeoPoint(51189608,8837888));
		retList.add( new GeoPoint(51324764,8678379));
		retList.add( new GeoPoint(51383349,8962738));
		retList.add( new GeoPoint(51486324,8966795));
		retList.add( new GeoPoint(51401445,9203395));
		retList.add( new GeoPoint(51637491,9466536));
		retList.add( new GeoPoint(51868528,9484001));
		retList.add( new GeoPoint(52290811,9053023));
		retList.add( new GeoPoint(52469818,9208064));
		retList.add( new GeoPoint(52519596,9081776));
		retList.add( new GeoPoint(52421375,8760976));
		retList.add( new GeoPoint(52568665,8693299));
		retList.add( new GeoPoint(52474751,8250405));
		retList.add( new GeoPoint(52175635,8434623));
		retList.add( new GeoPoint(52098970,8049264));
		retList.add( new GeoPoint(52377637,7992878));
		retList.add( new GeoPoint(52498058,7617781));
		retList.add( new GeoPoint(52259953,7061792));
		retList.add( new GeoPoint(51860880,5908173));
		retList.add( new GeoPoint(50791270,5815056));
		retList.add( new GeoPoint(50309047,6375108));
		return retList;
	}
	
	public static List<GeoPoint> createSachsenAnhaltCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(50918399,12243056));
		retList.add( new GeoPoint(51101047,12344860));
		retList.add( new GeoPoint(51304995,12209350));
		retList.add( new GeoPoint(51533379,12252008));
		retList.add( new GeoPoint(51640078,12823676));
		retList.add( new GeoPoint(51617001,13061375));
		retList.add( new GeoPoint(51713858,13216723));
		retList.add( new GeoPoint(51904281,13153914));
		retList.add( new GeoPoint(52134825,12317280));
		retList.add( new GeoPoint(52478932,12372258));
		retList.add( new GeoPoint(52557771,12221584));
		retList.add( new GeoPoint(52635833,12287853));
		retList.add( new GeoPoint(52879356,12262569));
		retList.add( new GeoPoint(52918739,11921355));
		retList.add( new GeoPoint(53056995,11623657));
		retList.add( new GeoPoint(52908018,11332249));
		retList.add( new GeoPoint(52930232,10993258));
		retList.add( new GeoPoint(52833118,10692496));
		retList.add( new GeoPoint(52599836,10892296));
		retList.add( new GeoPoint(52392035,10935525));
		retList.add( new GeoPoint(52227792,11009203));
		retList.add( new GeoPoint(52083317,10915292));
		retList.add( new GeoPoint(52042688,10551041));
		retList.add( new GeoPoint(51758500,10540787));
		retList.add( new GeoPoint(51620051,10682255));
		retList.add( new GeoPoint(51596670,10853670));
		retList.add( new GeoPoint(51410297,10960138));
		retList.add( new GeoPoint(51357254,11345291));
		retList.add( new GeoPoint(51199989,11352554));
		retList.add( new GeoPoint(51074800,11477883));
		retList.add( new GeoPoint(50918399,12243056));
		return retList;
	}
	
	public static List<GeoPoint> createBrandenburgCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(51647151,13032130));
		retList.add( new GeoPoint(51518022,13180529));
		retList.add( new GeoPoint(51391370,13151162));
		retList.add( new GeoPoint(51368162,13280614));
		retList.add( new GeoPoint(51421118,13395088));
		retList.add( new GeoPoint(51358442,13545805));
		retList.add( new GeoPoint(51358717,14010024));
		retList.add( new GeoPoint(51524117,14171362));
		retList.add( new GeoPoint(51486416,14322163));
		retList.add( new GeoPoint(51559138,14579581));
		retList.add( new GeoPoint(51534703,14635705));
		retList.add( new GeoPoint(51594454,14809465));
		retList.add( new GeoPoint(53419802,14639078));
		retList.add( new GeoPoint(53276395,14207373));
		retList.add( new GeoPoint(53280815,14148955));
		retList.add( new GeoPoint(53407462,14282121));
		retList.add( new GeoPoint(53457859,14227683));
		retList.add( new GeoPoint(53455617,13970734));
		retList.add( new GeoPoint(53584706,13783885));
		retList.add( new GeoPoint(53305420,13382926));
		retList.add( new GeoPoint(53207070,12917979));
		retList.add( new GeoPoint(53395223,12076262));
		retList.add( new GeoPoint(53255066,11787234));
		retList.add( new GeoPoint(53261036,11633920));
		retList.add( new GeoPoint(53206491,11520415));
		retList.add( new GeoPoint(53149378,11501882));
		retList.add( new GeoPoint(53126121,11223388));
		retList.add( new GeoPoint(53039164,11337796));
		retList.add( new GeoPoint(53058974,11452676));
		retList.add( new GeoPoint(52947385,11769991));
		retList.add( new GeoPoint(52900171,11808428));
		retList.add( new GeoPoint(52866773,12022773));
		retList.add( new GeoPoint(52837044,12215028));
		retList.add( new GeoPoint(52647128,12175826));
		retList.add( new GeoPoint(52505293,12113010));
		retList.add( new GeoPoint(52466121,12272029));
		retList.add( new GeoPoint(52341771,12262515));
		retList.add( new GeoPoint(52136304,12192131));
		retList.add( new GeoPoint(51974446,12470667));
		retList.add( new GeoPoint(51979336,12707206));
		retList.add( new GeoPoint(51847895,13095086));
		retList.add( new GeoPoint(51728516,13146565));
		retList.add( new GeoPoint(51647151,13032130));
		retList.addAll(createBerlinCoordinates());
		return retList;
	}
	
	public static List<GeoPoint> createNiedersachsenCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(52212695,7040836));
		retList.add( new GeoPoint(52267906,7355268));
		retList.add( new GeoPoint(52384746,7614190));
		retList.add( new GeoPoint(52457174,7618827));
		retList.add( new GeoPoint(52363026,7752595));
		retList.add( new GeoPoint(52355076,7916356));
		retList.add( new GeoPoint(52170618,7884994));
		retList.add( new GeoPoint(52155464,7972448));
		retList.add( new GeoPoint(52127539,7982061));
		retList.add( new GeoPoint(52080738,7846946));
		retList.add( new GeoPoint(52014604,7990615));
		retList.add( new GeoPoint(52105352,8304761));
		retList.add( new GeoPoint(52097600,8410823));
		retList.add( new GeoPoint(52180338,8527411));
		retList.add( new GeoPoint(52367290,8455564));
		retList.add( new GeoPoint(52409712,8336988));
		retList.add( new GeoPoint(52505225,8643430));
		retList.add( new GeoPoint(52380987,8688694));
		retList.add( new GeoPoint(52387538,8959881));
		retList.add( new GeoPoint(52477547,9070107));
		retList.add( new GeoPoint(52376151,9063337));
		retList.add( new GeoPoint(52270997,8915072));
		retList.add( new GeoPoint(52125328,9000712));
		retList.add( new GeoPoint(52118477,9097663));
		retList.add( new GeoPoint(51970179,9161907));
		retList.add( new GeoPoint(51843550,9322189));
		retList.add( new GeoPoint(51831793,9403217));
		retList.add( new GeoPoint(51647437,9345157));
		retList.add( new GeoPoint(51603125,9611825));
		retList.add( new GeoPoint(51342329,9529918));
		retList.add( new GeoPoint(51286549,9753790));
		retList.add( new GeoPoint(51352710,9822826));
		retList.add( new GeoPoint(51418079,10188523));
		retList.add( new GeoPoint(51566041,10430388));
		retList.add( new GeoPoint(51533629,10520235));
		retList.add( new GeoPoint(51548365,10700955));
		retList.add( new GeoPoint(51707237,10711785));
		retList.add( new GeoPoint(51821834,10603382));
		retList.add( new GeoPoint(51889536,10669672));
		retList.add( new GeoPoint(51997475,10643771));
		retList.add( new GeoPoint(52045488,10998631));
		retList.add( new GeoPoint(52176932,11096179));
		retList.add( new GeoPoint(52380486,11091735));
		retList.add( new GeoPoint(52462764,10969762));
		retList.add( new GeoPoint(52492718,11030770));
		retList.add( new GeoPoint(52623768,10998989));
		retList.add( new GeoPoint(52807420,10786717));
		retList.add( new GeoPoint(52884346,11071295));
		retList.add( new GeoPoint(52851497,11426200));
		retList.add( new GeoPoint(53045600,11625979));
		retList.add( new GeoPoint(53103187,11332101));
		retList.add( new GeoPoint(53360824,10957083));
		retList.add( new GeoPoint(53324982,10812667));
		retList.add( new GeoPoint(53390158,10727800));
		retList.add( new GeoPoint(53381684,10544596));
		retList.add( new GeoPoint(53381237,10540307));
		retList.add( new GeoPoint(53451583,10307157));
		retList.add( new GeoPoint(53407641,10191864));
		retList.add( new GeoPoint(53475726,10064874));
		retList.add( new GeoPoint(53432747,9984921));
		retList.add( new GeoPoint(53467807,9823448));
		retList.add( new GeoPoint(53580780,9770507));
		retList.add( new GeoPoint(53599349,9604532));
		retList.add( new GeoPoint(53882167,9294273));
		retList.add( new GeoPoint(53886535,8933025));
		retList.add( new GeoPoint(54054579,8271205));
		retList.add( new GeoPoint(53524530,5192737));
		retList.add( new GeoPoint(52212695,7040836));
		retList.addAll(createBremenCoordinates());
		return retList;
	}
	
	public static List<GeoPoint> createSchleswigHolsteinCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(53431952,10302901));
		retList.add( new GeoPoint(53343970,10602071));
		retList.add( new GeoPoint(53446043,10656173));
		retList.add( new GeoPoint(53445720,10721653));
		retList.add( new GeoPoint(53506412,10848231));
		retList.add( new GeoPoint(53557698,10848002));
		retList.add( new GeoPoint(53566219,10944159));
		retList.add( new GeoPoint(53692067,10973505));
		retList.add( new GeoPoint(53759167,10784884));
		retList.add( new GeoPoint(53851552,10768632));
		retList.add( new GeoPoint(53914335,10883295));
		retList.add( new GeoPoint(53893156,10905573));
		retList.add( new GeoPoint(53905371,10977690));
		retList.add( new GeoPoint(53919730,10966290));
		retList.add( new GeoPoint(53928502,10902327));
		retList.add( new GeoPoint(54419952,11350157));
		retList.add( new GeoPoint(54537640,11212029));
		retList.add( new GeoPoint(54977740,9302356));
		retList.add( new GeoPoint(55082068,8313033));
		retList.add( new GeoPoint(54255479,8167742));
		retList.add( new GeoPoint(53862416,8927421));
		retList.add( new GeoPoint(53869209,9292768));
		retList.add( new GeoPoint(53746193,9435706));
		retList.add( new GeoPoint(53596248,9584290));
		retList.add( new GeoPoint(53557191,9729407));
		retList.add( new GeoPoint(53612613,9765776));
		retList.add( new GeoPoint(53578751,9825117));
		retList.add( new GeoPoint(53648022,9926304));
		retList.add( new GeoPoint(53644289,9991593));
		retList.add( new GeoPoint(53676038,10005174));
		retList.add( new GeoPoint(53675551,10071313));
		retList.add( new GeoPoint(53712605,10087802));
		retList.add( new GeoPoint(53709988,10128847));
		retList.add( new GeoPoint(53731473,10168893));
		retList.add( new GeoPoint(53724107,10183887));
		retList.add( new GeoPoint(53679474,10133742));
		retList.add( new GeoPoint(53655468,10179918));
		retList.add( new GeoPoint(53632900,10188478));
		retList.add( new GeoPoint(53631588,10209505));
		retList.add( new GeoPoint(53614381,10180186));
		retList.add( new GeoPoint(53590615,10192182));
		retList.add( new GeoPoint(53589494,10156028));
		retList.add( new GeoPoint(53560281,10139971));
		retList.add( new GeoPoint(53513381,10163917));
		retList.add( new GeoPoint(53510075,10204815));
		retList.add( new GeoPoint(53431952,10302901));
		return retList;
	}
	
	public static List<GeoPoint> createMecklenburgVorpommernCoordinates() {
		ArrayList<GeoPoint> retList = new ArrayList<GeoPoint>();
		retList.add( new GeoPoint(53096291,11274577));
		retList.add( new GeoPoint(53113379,11585443));
		retList.add( new GeoPoint(53189622,11601758));
		retList.add( new GeoPoint(53214779,11685441));
		retList.add( new GeoPoint(53194943,11814358));
		retList.add( new GeoPoint(53325787,12082109));
		retList.add( new GeoPoint(53290761,12351636));
		retList.add( new GeoPoint(53230713,12444538));
		retList.add( new GeoPoint(53245548,12534399));
		retList.add( new GeoPoint(53189662,12709988));
		retList.add( new GeoPoint(53149601,13008577));
		retList.add( new GeoPoint(53227337,13165687));
		retList.add( new GeoPoint(53194855,13220430));
		retList.add( new GeoPoint(53255696,13342572));
		retList.add( new GeoPoint(53230596,13396357));
		retList.add( new GeoPoint(53302398,13543073));
		retList.add( new GeoPoint(53387496,13595830));
		retList.add( new GeoPoint(53461554,13739592));
		retList.add( new GeoPoint(53486475,13851671));
		retList.add( new GeoPoint(53400447,13895757));
		retList.add( new GeoPoint(53414598,14221867));
		retList.add( new GeoPoint(53250727,14073812));
		retList.add( new GeoPoint(53245937,14280319));
		retList.add( new GeoPoint(53327237,14437826));
		retList.add( new GeoPoint(54247616,14302103));
		retList.add( new GeoPoint(54751000,13435657));
		retList.add( new GeoPoint(54020653,10865493));
		retList.add( new GeoPoint(53835792,10706907));
		retList.add( new GeoPoint(53723640,10741384));
		retList.add( new GeoPoint(53662673,10908621));
		retList.add( new GeoPoint(53459922,10604014));
		retList.add( new GeoPoint(53335455,10574244));
		retList.add( new GeoPoint(53282587,10855208));
		retList.add( new GeoPoint(53307261,10962977));
		retList.add( new GeoPoint(53159202,11131625));
		retList.add( new GeoPoint(53096291,11274577));
		return retList;
	}
}
