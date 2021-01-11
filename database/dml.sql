USE LegisRate;

INSERT  INTO Legislation (LegislationTitle, Summary, Enacted)  VALUES 
("Efficient Use of Government Spectrum Act of 2013",
"Efficient Use of Government Spectrum Act of 2013 - Directs the Federal Communications Commission (FCC), within three years after enactment of the Middle Class Tax Relief and Job Creation Act of 2012, to: (1) reallocate electromagnetic spectrum between the frequencies from 1755 to 1780 megahertz (currently, such frequencies are occupied by the Department of Defense [DOD] and other federal agencies); and (2) as part of the competitive bidding auctions required by such Act, grant new initial lic...", false ),
( "Reducing Employer Burdens, Unleashing Innovation, and Labor Development Act of 2013", 
"Reducing Employer Burdens, Unleashing Innovation, and Labor Development Act of 2013 - Expresses the sense of Congress that increasing the competitiveness of U.S. manufacturers will strengthen the national economy. Title I: Investing in America&#39;s Workforce - Investing in America&#39;s Workforce Act - Amends the Workforce Investment Act of 1998 to require state or local workforce investment systems to use youth activities funds allocated to a local area for programs that provide training, which may...", false),
("Wireless Innovation Act of 2014",
"Wireless Innovation Act of 2014 - Amends the National Telecommunications and Information Administration Organization Act to require the Secretary of Commerce to report to the President and Congress with recommendations to reallocate a span of at least 200 megahertz of spectrum, located below 5 gigahertz, from federal government use to: (1) commercial use on an exclusive, licensed basis; (2) unlicensed use to protect licensed services from harmful interference; and (3) shared use between feder...", false),
( "Promoting Unlicensed Spectrum Act of 2015",
"Promoting Unlicensed Spectrum Act of 2015  This bill requires the Federal Communications Commission (FCC) to ensure that spectrum allocation and assignment produces a balance between radio frequency bands available for: (1) exclusive licensing through an auction, and (2) unlicensed operations on a nonexclusive basis without the expectation of protection from interference.  The FCC must consider whether to adopt rules that permit unlicensed operations in spectrum assigned by auction until the ...", false),
( "Wireless Innovation Act of 2015", 
"Wireless Innovation Act of 2015 Amends the National Telecommunications and Information Administration Organization Act to require the Secretary of Commerce to report to the President and Congress with recommendations to reallocate a span of at least 200 megahertz of spectrum, located below 5 gigahertz, from federal government use to: (1) commercial use on an exclusive, licensed basis; (2) unlicensed use to protect licensed services from harmful interference; and (3) shared use between federal...", false),
( "MOBILE NOW Act", 
"Making Opportunities for Broadband Investment and Limiting Excessive and Needless Obstacles to Wireless Act or the MOBILE NOW Act  (Sec. 3) This bill requires the National Telecommunications and Information Administration (NTIA) and the Federal Communications Commission (FCC), by December 31, 2020, to make available at least 255 megahertz of federal and nonfederal spectrum below the frequency of 6000 megahertz for mobile and fixed wireless broadband use. At least: (1) 100 megahertz shall be m...",  false);


-- Sample Users
INSERT INTO Users (Username, State) VALUES 
("Aosaliu", "NY"), ("Rdtaylorjr", "NY"), ("JMRondello", "NY"), ("RoseBudmaria", "NY");


-- Sample Reviews
INSERT INTO Review (LegislatureId, UserId, UserComment, Rating ) VALUES 
("1", "1", "This is a terrible bill, I feel that it does not adequantly attend to the needs of the people" , "1"), 
("2", "3", "I like this bill and believe it should be passed as it gives me the opportunity to change my life" , "5"),
("3", "4" ,  "Im neutral and dont care if this bill is passed or not", "3");

