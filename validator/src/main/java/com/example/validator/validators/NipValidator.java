package com.example.validator.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NipValidator implements ConstraintValidator<ValidNip, String> {

	private int min;
	
	private static byte NIP[] = new byte[10];
	private static boolean valid = false;
	private static int DepNo[] = { 101, 102, 103, 104, 105, 106, 107, 108, 109, 111, 112, 113, 114, 115, 116, 117, 118,
			119, 121, 122, 123, 124, 125, 126, 127, 128, 129, 131, 132, 133, 134, 135, 136, 137, 138, 139, 141, 142,
			143, 144, 145, 146, 147, 148, 149, 151, 152, 153, 154, 155, 156, 157, 158, 159, 161, 162, 163, 164, 165,
			166, 167, 168, 169, 171, 172, 173, 174, 175, 176, 177, 178, 179, 181, 182, 183, 184, 185, 186, 187, 188,
			189, 191, 192, 193, 194, 195, 196, 197, 198, 199, 201, 202, 203, 204, 205, 206, 207, 208, 209, 211, 212,
			213, 214, 215, 216, 217, 218, 219, 221, 222, 223, 224, 225, 226, 227, 228, 229, 231, 232, 233, 234, 235,
			236, 237, 238, 239, 241, 242, 243, 244, 245, 246, 247, 248, 249, 251, 252, 253, 254, 255, 256, 257, 258,
			259, 261, 262, 263, 264, 265, 266, 267, 268, 269, 271, 272, 273, 274, 275, 276, 277, 278, 279, 281, 282,
			283, 284, 285, 286, 287, 288, 289, 291, 292, 293, 294, 295, 296, 297, 298, 301, 302, 311, 312, 313, 314,
			315, 316, 317, 318, 319, 321, 322, 323, 324, 325, 326, 327, 328, 329, 331, 332, 333, 334, 335, 336, 337,
			338, 339, 341, 342, 343, 344, 345, 346, 347, 348, 349, 351, 352, 353, 354, 355, 356, 357, 358, 359, 361,
			362, 363, 364, 365, 366, 367, 368, 369, 371, 372, 373, 374, 375, 376, 377, 378, 379, 381, 382, 383, 384,
			385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 411, 412, 413, 414, 415, 416, 417,
			418, 419, 421, 422, 423, 424, 425, 426, 427, 428, 429, 431, 432, 433, 434, 435, 436, 437, 438, 439, 441,
			442, 443, 444, 445, 446, 447, 448, 449, 451, 452, 453, 454, 455, 456, 457, 458, 459, 461, 462, 463, 464,
			465, 466, 467, 468, 469, 471, 472, 473, 474, 475, 476, 477, 478, 479, 481, 482, 483, 484, 485, 486, 487,
			488, 489, 491, 492, 493, 494, 495, 496, 497, 498, 499, 501, 502, 503, 504, 505, 506, 507, 508, 509, 511,
			512, 513, 514, 516, 519, 521, 522, 523, 524, 525, 526, 527, 528, 529, 531, 532, 533, 534, 535, 536, 537,
			538, 539, 541, 542, 543, 544, 545, 546, 547, 548, 549, 551, 552, 553, 554, 555, 556, 557, 558, 559, 561,
			562, 563, 564, 565, 566, 567, 568, 569, 571, 572, 573, 574, 575, 576, 577, 578, 579, 581, 582, 583, 584,
			585, 586, 587, 588, 589, 591, 592, 593, 594, 595, 596, 597, 598, 599, 601, 602, 603, 604, 605, 606, 607,
			608, 609, 611, 612, 613, 614, 615, 616, 617, 618, 619, 621, 622, 623, 624, 625, 626, 627, 628, 629, 631,
			632, 633, 634, 635, 636, 637, 638, 639, 641, 642, 643, 644, 645, 646, 647, 648, 649, 651, 652, 653, 654,
			655, 656, 657, 658, 659, 661, 662, 663, 664, 665, 666, 667, 668, 669, 671, 672, 673, 674, 675, 676, 677,
			678, 679, 681, 682, 683, 684, 685, 686, 687, 688, 689, 691, 692, 693, 694, 695, 696, 697, 698, 699, 701,
			711, 712, 713, 714, 715, 716, 717, 718, 719, 721, 722, 723, 724, 725, 726, 727, 728, 729, 731, 732, 733,
			734, 735, 736, 737, 738, 739, 741, 742, 743, 744, 745, 746, 747, 748, 749, 751, 752, 753, 754, 755, 756,
			757, 758, 759, 761, 762, 763, 764, 765, 766, 767, 768, 769, 771, 772, 773, 774, 775, 776, 777, 778, 779,
			781, 782, 783, 784, 785, 786, 787, 788, 789, 791, 792, 793, 794, 795, 796, 797, 798, 799, 811, 812, 813,
			814, 815, 816, 817, 818, 819, 821, 822, 823, 824, 825, 826, 827, 828, 829, 831, 832, 833, 834, 835, 836,
			837, 838, 839, 841, 842, 843, 844, 845, 846, 847, 848, 849, 851, 852, 853, 854, 855, 856, 857, 858, 859,
			861, 862, 863, 864, 865, 866, 867, 868, 869, 871, 872, 873, 874, 875, 876, 877, 878, 879, 881, 882, 883,
			884, 885, 886, 887, 888, 889, 891, 892, 893, 894, 895, 896, 897, 898, 899, 911, 912, 913, 914, 915, 916,
			917, 918, 919, 921, 922, 923, 924, 925, 926, 927, 928, 929, 931, 932, 933, 934, 935, 936, 937, 938, 939,
			941, 942, 943, 944, 945, 946, 947, 948, 949, 951, 952, 953, 954, 955, 956, 957, 958, 959, 961, 962, 963,
			964, 965, 966, 967, 968, 969, 971, 972, 973, 974, 975, 976, 977, 978, 979, 981, 982, 983, 984, 985, 986,
			987, 988, 989, 991, 992, 993, 994, 995, 996, 997, 998 };

	private static String DepName[] = { "Dolnośląski Urząd Skarbowy we Wrocławiu",
			"Kujawsko-Pomorski Urząd Skarbowy w Bydgoszczy", "Lubelski Urząd Skarbowy w Lublinie",
			"Lubuski Urząd Skarbowy w Zielonej Górze", "Łódzki Urząd Skarbowy w Łodzi",
			"Małopolski Urząd Skarbowy w Krakowie", "Pierwszy Mazowiecki Urząd Skarbowy w Warszawie",
			"Drugi Mazowiecki Urząd Skarbowy w Warszawie", "Trzeci Mazowiecki Urząd Skarbowy w Radomiu",
			"Urząd Skarbowy Warszawa-Mokotów", "Urząd Skarbowy Warszawa-Bemowo", "Urząd Skarbowy Warszawa-Praga",
			"Urząd Skarbowy Warszawa-Targówek", "Pierwszy Urząd Skarbowy Warszawa-Śródmieście",
			"Drugi Urząd Skarbowy Warszawa-Śródmieście", "Urząd Skarbowy Warszawa-Wola",
			"Urząd Skarbowy Warszawa-Bielany", "Urząd Skarbowy w Grodzisku Mazowieckim",
			"Urząd Skarbowy w Nowym Dworze Mazowieckim", "Urząd Skarbowy w Otwocku", "Urząd Skarbowy w Piasecznie",
			"Urząd Skarbowy w Pruszkowie", "Urząd Skarbowy w Wołominie", "Urząd Skarbowy w Białej Podlaskiej",
			"Urząd Skarbowy w Parczewie", "Urząd Skarbowy w Radzyniu Podlaskim",
			"Pierwszy Urząd Skarbowy w Białymstoku", "Urząd Skarbowy w Bielsku Podlaskim", "Urząd Skarbowy w Mońkach",
			"Urząd Skarbowy w Siemiatyczach", "Urząd Skarbowy w Sokółce", "Pierwszy Urząd Skarbowy w Bielsku-Białej",
			"Urząd Skarbowy w Cieszynie", "Urząd Skarbowy w Oświęcimiu", "Urząd Skarbowy w Suchej Beskidzkiej",
			"Urząd Skarbowy w Wadowicach", "Urząd Skarbowy w Żywcu", "Pierwszy Urząd Skarbowy w Bydgoszczy",
			"Urząd Skarbowy w Chojnicach", "Urząd Skarbowy w Inowrocławiu", "Urząd Skarbowy w Mogilnie",
			"Urząd Skarbowy w Nakle nad Notecią ", "Urząd Skarbowy w Świeciu", "Urząd Skarbowy w Tucholi",
			"Urząd Skarbowy w Żninie", "Urząd Skarbowy w Chełmie", "Urząd Skarbowy w Krasnymstawie",
			"Urząd Skarbowy we Włodawie", "Urząd Skarbowy w Ciechanowie", "Urząd Skarbowy w Działdowie",
			"Urząd Skarbowy w Mławie", "Urząd Skarbowy w Płońsku", "Urząd Skarbowy w Pułtusku",
			"Pierwszy Urząd Skarbowy w Częstochowie", "Urząd Skarbowy w Kłobucku", "Urząd Skarbowy w Lublińcu",
			"Urząd Skarbowy w Myszkowie", "Urząd Skarbowy w Oleśnie", "Urząd Skarbowy w Braniewie",
			"Urząd Skarbowy w Elblągu", "Urząd Skarbowy w Kwidzynie", "Urząd Skarbowy w Malborku",
			"Pierwszy Urząd Skarbowy w Gdańsku", "Drugi Urząd Skarbowy w Gdańsku", "Pierwszy Urząd Skarbowy w Gdyni",
			"Urząd Skarbowy w Kartuzach", "Urząd Skarbowy w Kościerzynie", "Urząd Skarbowy w Pucku",
			"Urząd Skarbowy w Sopocie", "Urząd Skarbowy w Starogardzie Gdańskim", "Urząd Skarbowy w Tczewie",
			"Urząd Skarbowy w Wejherowie", "Urząd Skarbowy w Choszcznie", "Urząd Skarbowy w Gorzowie Wielkopolskim",
			"Urząd Skarbowy w Myśliborzu", "Urząd Skarbowy w Międzychodzie", "Urząd Skarbowy w Międzyrzeczu",
			"Urząd Skarbowy w Słubicach", "Urząd Skarbowy w Bolesławcu", "Urząd Skarbowy w Jeleniej Górze",
			"Urząd Skarbowy w Kamiennej Górze", "Urząd Skarbowy w Lubaniu", "Urząd Skarbowy w Lwówku Śląskim",
			"Urząd Skarbowy w Zgorzelcu", "Urząd Skarbowy w Jarocinie", "Pierwszy Urząd Skarbowy w Kaliszu",
			"Urząd Skarbowy w Kępnie", "Urząd Skarbowy w Krotoszynie", "Urząd Skarbowy w Ostrowie Wielkopolskim",
			"Urząd Skarbowy w Będzinie", "Opolski Urząd Skarbowy w Opolu", "Podkarpacki Urząd Skarbowy w Rzeszowie",
			"Podlaski Urząd Skarbowy w Białymstoku", "Pomorski Urząd Skarbowy w Gdańsku",
			"Pierwszy Śląski Urząd Skarbowy w Sosnowcu", "Drugi Śląski Urząd Skarbowy w Bielsku-Białej",
			"Świętokrzyski Urząd Skarbowy w Kielcach", "Warmińsko-Mazurski Urząd Skarbowy w Olsztynie",
			"Pierwszy Wielkopolski Urząd Skarbowy w Poznaniu", "Urząd Skarbowy w Bytomiu", "Urząd Skarbowy w Chorzowie",
			"Urząd Skarbowy w Chrzanowie", "Urząd Skarbowy w Czechowicach-Dziedzicach",
			"Urząd Skarbowy w Dąbrowie Górniczej", "Pierwszy Urząd Skarbowy w Gliwicach",
			"Urząd Skarbowy w Jastrzębiu-Zdroju", "Urząd Skarbowy w Jaworznie", "Pierwszy Urząd Skarbowy w Katowicach",
			"Urząd Skarbowy w Mikołowie", "Urząd Skarbowy w Mysłowicach", "Urząd Skarbowy w Olkuszu",
			"Urząd Skarbowy w Pszczynie", "Urząd Skarbowy w Raciborzu", "Urząd Skarbowy w Rudzie Śląskiej",
			"Urząd Skarbowy w Rybniku", "Urząd Skarbowy w Siemianowicach Śląskich", "Urząd Skarbowy w Sosnowcu",
			"Urząd Skarbowy w Tarnowskich Górach", "Urząd Skarbowy w Tychach", "Urząd Skarbowy w Wodzisławiu Śląskim",
			"Urząd Skarbowy w Zabrzu", "Urząd Skarbowy w Zawierciu", "Urząd Skarbowy w Żorach",
			"Urząd Skarbowy w Busku-Zdroju", "Urząd Skarbowy w Jędrzejowie", "Pierwszy Urząd Skarbowy w Kielcach",
			"Urząd Skarbowy w Końskich", "Urząd Skarbowy w Miechowie", "Urząd Skarbowy w Ostrowcu Świętokrzyskim",
			"Urząd Skarbowy w Pińczowie", "Urząd Skarbowy w Starachowicach", "Urząd Skarbowy w Skarżysku-Kamiennej",
			"Urząd Skarbowy w Kole", "Urząd Skarbowy w Koninie", "Urząd Skarbowy w Słupcy", "Urząd Skarbowy w Turku",
			"Urząd Skarbowy w Białogardzie", "Urząd Skarbowy w Drawsku Pomorskim", "Urząd Skarbowy w Kołobrzegu",
			"Pierwszy Urząd Skarbowy w Koszalinie", "Urząd Skarbowy w Szczecinku", "Urząd Skarbowy Kraków-Krowodrza",
			"Urząd Skarbowy Kraków-Nowa Huta", "Urząd Skarbowy Kraków-Podgórze", "Urząd Skarbowy w Kraków-Śródmieście",
			"Urząd Skarbowy w Kraków-Stare Miasto", "Urząd Skarbowy w Myślenicach", "Urząd Skarbowy w Proszowicach",
			"Urząd Skarbowy w Wieliczce", "Urząd Skarbowy w Brzozowie", "Urząd Skarbowy w Jaśle",
			"Urząd Skarbowy w Krośnie", "Urząd Skarbowy w Lesku", "Urząd Skarbowy w Sanoku",
			"Urząd Skarbowy w Ustrzykach Dolnych", "Urząd Skarbowy w Głogowie", "Urząd Skarbowy w Jaworze",
			"Urząd Skarbowy w Legnicy", "Urząd Skarbowy w Lubinie", "Urząd Skarbowy w Złotoryi",
			"Urząd Skarbowy w Gostyniu", "Urząd Skarbowy w Kościanie", "Urząd Skarbowy w Lesznie",
			"Urząd Skarbowy w Rawiczu", "Urząd Skarbowy w Kraśniku", "Urząd Skarbowy w Lubartowie",
			"Pierwszy Urząd Skarbowy w Lublinie", "Drugi Urząd Skarbowy w Lublinie", "Urząd Skarbowy w Opolu Lubelskim",
			"Urząd Skarbowy w Puławach", "Urząd Skarbowy w Grajewie", "Urząd Skarbowy w Kolnie",
			"Urząd Skarbowy w Łomży", "Urząd Skarbowy w Wysokiem Mazowieckim", "Urząd Skarbowy w Zambrowie",
			"Urząd Skarbowy w Głownie", "Pierwszy Urząd Skarbowy Łódź-Bałuty", "Pierwszy Urząd Skarbowy Łódź-Górna",
			"Urząd Skarbowy Łódź-Polesie", "Drugi Wielkopolski Urząd Skarbowy w Kaliszu",
			"Zachodniopomorski Urząd Skarbowy w Szczecinie", "Urząd Skarbowy Łódź-Śródmieście",
			"Urząd Skarbowy Łódź-Widzew", "Urząd Skarbowy w Pabianicach", "Urząd Skarbowy w Zgierzu",
			"Urząd Skarbowy w Gorlicach", "Urząd Skarbowy w Limanowej", "Urząd Skarbowy w Nowym Sączu",
			"Urząd Skarbowy w Nowym Targu", "Urząd Skarbowy w Zakopanem", "Urząd Skarbowy w Bartoszycach",
			"Urząd Skarbowy w Iławie", "Urząd Skarbowy w Kętrzynie", "Urząd Skarbowy w Olsztynie",
			"Urząd Skarbowy w Ostródzie", "Urząd Skarbowy w Szczytnie", "Urząd Skarbowy w Brzegu",
			"Urząd Skarbowy w Głubczycach", "Urząd Skarbowy w Kędzierzynie-Koźlu", "Urząd Skarbowy w Kluczborku",
			"Urząd Skarbowy w Namysłowie", "Urząd Skarbowy w Nysie", "Pierwszy Urząd Skarbowy w Opolu",
			"Urząd Skarbowy w Prudniku", "Urząd Skarbowy w Strzelcach Opolskich",
			"Urząd Skarbowy w Makowie Mazowieckim", "Urząd Skarbowy w Ostrołęce",
			"Urząd Skarbowy w Ostrowi Mazowieckiej", "Urząd Skarbowy w Przasnyszu", "Urząd Skarbowy w Wyszkowie",
			"Urząd Skarbowy w Czarnkowie", "Urząd Skarbowy w Pile", "Urząd Skarbowy w Wałczu",
			"Urząd Skarbowy w Wągrowcu", "Urząd Skarbowy w Złotowie", "Urząd Skarbowy w Bełchatowie",
			"Urząd Skarbowy w Opocznie", "Urząd Skarbowy w Piotrkowie Trybunalskim", "Urząd Skarbowy w Radomsku",
			"Urząd Skarbowy w Tomaszowie Mazowieckim", "Urząd Skarbowy w Kutnie", "Urząd Skarbowy w Płocku",
			"Urząd Skarbowy w Sierpcu", "Urząd Skarbowy w Gnieźnie", "Urząd Skarbowy w Nowym Tomyślu",
			"Urząd Skarbowy Poznań-Grunwald", "Urząd Skarbowy Poznań-Jeżyce", "Urząd Skarbowy Poznań-Nowe Miasto",
			"Pierwszy Urząd Skarbowy Poznań ", "Urząd Skarbowy Poznań-Śródmieście", "Urząd Skarbowy Poznań-Wilda",
			"Urząd Skarbowy w Szamotułach", "Urząd Skarbowy w Śremie", "Urząd Skarbowy w Środzie Wielkopolskiej",
			"Urząd Skarbowy we Wrześni", "Urząd Skarbowy w Jarosławiu", "Urząd Skarbowy w Lubaczowie",
			"Urząd Skarbowy w Przemyślu", "Urząd Skarbowy w Przeworsku", "Urząd Skarbowy w Białobrzegach",
			"Urząd Skarbowy w Grójcu", "Urząd Skarbowy w Kozienicach", "Pierwszy Urząd Skarbowy w Radomiu",
			"Urząd Skarbowy w Szydłowcu", "Urząd Skarbowy w Zwoleniu", "Urząd Skarbowy w Kolbuszowej",
			"Urząd Skarbowy w Leżajsku", "Urząd Skarbowy w Łańcucie", "Urząd Skarbowy w Mielcu",
			"Urząd Skarbowy w Ropczycach", "Urząd Skarbowy w Rzeszowie", "Urząd Skarbowy w Strzyżowie",
			"Urząd Skarbowy w Garwolinie", "Urząd Skarbowy w Łukowie", "Urząd Skarbowy w Mińsku Mazowieckim",
			"Urząd Skarbowy w Siedlcach", "Urząd Skarbowy w Sokołowie Podlaskim", "Urząd Skarbowy w Węgrowie",
			"Urząd Skarbowy w Łasku", "Urząd Skarbowy w Poddębicach", "Urząd Skarbowy w Sieradzu",
			"Urząd Skarbowy w Wieluniu", "Urząd Skarbowy w Zduńskiej Woli", "Urząd Skarbowy w Brzezinach",
			"Urząd Skarbowy w Łowiczu", "Urząd Skarbowy w Rawie Mazowieckiej", "Urząd Skarbowy w Skierniewicach",
			"Urząd Skarbowy w Sochaczewie", "Urząd Skarbowy w Żyrardowie", "Urząd Skarbowy w Bytowie",
			"Urząd Skarbowy w Człuchowie", "Urząd Skarbowy w Lęborku", "Urząd Skarbowy w Słupsku",
			"Urząd Skarbowy w Augustowie", "Urząd Skarbowy w Ełku", "Urząd Skarbowy w Giżycku",
			"Urząd Skarbowy w Olecku", "Urząd Skarbowy w Piszu", "Urząd Skarbowy w Suwałkach",
			"Urząd Skarbowy w Goleniowie", "Urząd Skarbowy w Gryficach", "Urząd Skarbowy w Gryfinie",
			"Urząd Skarbowy w Pyrzycach", "Urząd Skarbowy w Stargardzie Szczecińskim",
			"Pierwszy Urząd Skarbowy w Szczecinie", "Drugi Urząd Skarbowy w Szczecinie", "Urząd Skarbowy w Świnoujściu",
			"Urząd Skarbowy w Janowie Lubelskim", "Urząd Skarbowy w Opatowie", "Urząd Skarbowy w Sandomierzu",
			"Urząd Skarbowy w Stalowej Woli", "Urząd Skarbowy w Staszowie", "Urząd Skarbowy w Tarnobrzegu",
			"Urząd Skarbowy w Bochni", "Urząd Skarbowy w Brzesku", "Urząd Skarbowy w Dąbrowie Tarnowskiej",
			"Urząd Skarbowy w Dębicy", "Pierwszy Urząd Skarbowy w Tarnowie", "Urząd Skarbowy w Brodnicy",
			"Urząd Skarbowy w Chełmnie", "Urząd Skarbowy w Grudziądzu", "Urząd Skarbowy w Nowym Mieście Lubawskim",
			"Drugi Urząd Skarbowy w Toruniu", "Urząd Skarbowy w Wąbrzeźnie", "Urząd Skarbowy w Bystrzycy Kłodzkiej",
			"Urząd Skarbowy w Dzierżoniowie", "Urząd Skarbowy w Kłodzku", "Urząd Skarbowy w Nowej Rudzie",
			"Urząd Skarbowy w Świdnicy", "Urząd Skarbowy w Wałbrzychu", "Urząd Skarbowy w Ząbkowicach Śląskich",
			"Urząd Skarbowy w Aleksandrowie Kujawskim", "Urząd Skarbowy w Lipnie", "Urząd Skarbowy w Radziejowie",
			"Urząd Skarbowy w Rypinie", "Urząd Skarbowy we Włocławku", "Urząd Skarbowy Wrocław-Fabryczna",
			"Urząd Skarbowy Wrocław-Krzyki", "Urząd Skarbowy Wrocław-Psie Pole", "Urząd Skarbowy Wrocław-Stare Miasto",
			"Pierwszy Urząd Skarbowy we Wrocławiu", "Drugi Urząd Skarbowy Wrocław-Śródmieście",
			"Urząd Skarbowy w Miliczu", "Urząd Skarbowy w Oleśnicy", "Urząd Skarbowy w Oławie",
			"Urząd Skarbowy w Strzelnie", "Urząd Skarbowy w Środzie Śląskiej", "Urząd Skarbowy w Trzebnicy",
			"Urząd Skarbowy w Biłgoraju", "Urząd Skarbowy w Hrubieszowie", "Urząd Skarbowy w Tomaszowie Lubelskim",
			"Urząd Skarbowy w Zamościu", "Urząd Skarbowy w Krośnie Odrzańskim", "Urząd Skarbowy w Nowej Soli",
			"Urząd Skarbowy w Świebodzinie", "Urząd Skarbowy w Wolsztynie", "Pierwszy Urząd Skarbowy w Zielonej Górze",
			"Urząd Skarbowy w Żaganiu", "Urząd Skarbowy w Żarach", "Urząd Skarbowy w Łosicach",
			"Urząd Skarbowy w Łosicach", "Urząd Skarbowy w Piekarach Śląskich", "Drugi Urząd Skarbowy w Koszalinie",
			"Urząd Skarbowy W Górze", "Urząd Skarbowy W Polkowicach", "Urząd Skarbowy W Golubiu-Dobrzyniu",
			"Urząd Skarbowy W Sepólnie Krajeńskim", "Urząd Skarbowy W Łęcznej", "Urząd Skarbowy W Rykach",
			"Urząd Skarbowy W Łęczycy", "Urząd Skarbowy W Pajęcznie", "Urząd Skarbowy W Lipsku",
			"Urząd Skarbowy w Żurominie", "Urząd Skarbowy w Żurominie", "Urząd Skarbowy w Lesznie",
			"Urząd Skarbowy w Ostrzeszowie", "Urząd Skarbowy w Ostrzeszowie", "Urząd Skarbowy w Legionowie",
			"Urząd Skarbowy Warszawa-Mokotów", "Urząd Skarbowy Warszawa-Bemowo", "Urząd Skarbowy Warszawa-Praga",
			"Urząd Skarbowy Warszawa-Targówek", "Pierwszy Urząd Skarbowy Warszawa-Śródmieście",
			"Drugi Urząd Skarbowy Warszawa-Śródmieście", "Urząd Skarbowy Warszawa-Wola",
			"Urząd Skarbowy Warszawa-Bielany", "Urząd Skarbowy w Grodzisku Mazowieckim",
			"Urząd Skarbowy w Nowym Dworze Mazowieckim", "Urząd Skarbowy w Otwocku", "Urząd Skarbowy w Piasecznie",
			"Urząd Skarbowy w Pruszkowie", "Urząd Skarbowy w Wołominie", "Urząd Skarbowy w Legionowie",
			"Urząd Skarbowy w Białej Podlaskiej", "Urząd Skarbowy w Radzyniu Podlaskim", "Urząd Skarbowy w Parczewie",
			"Filia US w Białej Podlaskiej/Łosice", "Pierwszy Urząd Skarbowy w Białymstoku",
			"Urząd Skarbowy w Bielsku Podlaskim", "Urząd Skarbowy w Siemiatyczach", "Urząd Skarbowy w Sokółce",
			"Urząd Skarbowy w Mońkach", "Pierwszy Urząd Skarbowy w Bielsku-Białej", "Urząd Skarbowy w Cieszynie",
			"Urząd Skarbowy w Oświęcimiu", "Urząd Skarbowy w Wadowicach", "Urząd Skarbowy w Suchej Beskidzkiej",
			"Urząd Skarbowy w Żywcu", "Pierwszy Urząd Skarbowy w Bydgoszczy", "Urząd Skarbowy w Chojnicach",
			"Urząd Skarbowy w Inowrocławiu", "Urząd Skarbowy w Mogilnie", "Urząd Skarbowy w Nakle nad Notecią ",
			"Urząd Skarbowy w Świeciu", "Urząd Skarbowy w Tucholi", "Urząd Skarbowy w Żninie",
			"Urząd Skarbowy w Chełmie", "Urząd Skarbowy w Krasnymstawie", "Urząd Skarbowy we Włodawie",
			"Urząd Skarbowy w Ciechanowie", "Urząd Skarbowy w Płońsku", "Urząd Skarbowy w Pułtusku",
			"Urząd Skarbowy w Mławie", "Urząd Skarbowy w Działdowie", "Filia US w Mławie/Żuromin",
			"Pierwszy Urząd Skarbowy w Częstochowie", "Urząd Skarbowy w Kłobucku", "Urząd Skarbowy w Lublińcu",
			"Urząd Skarbowy w Oleśnie", "Urząd Skarbowy w Myszkowie", "Urząd Skarbowy w Elblągu",
			"Urząd Skarbowy w Malborku", "Urząd Skarbowy w Kwidzynie", "Urząd Skarbowy w Braniewie",
			"Pierwszy Urząd Skarbowy w Gdańsku", "Drugi Urząd Skarbowy w Gdańsku", "Urząd Skarbowy w Sopocie",
			"Pierwszy Urząd Skarbowy w Gdyni", "Urząd Skarbowy w Pucku", "Urząd Skarbowy w Wejherowie",
			"Urząd Skarbowy w Kartuzach", "Urząd Skarbowy w Kościerzynie", "Urząd Skarbowy w Starogardzie Gdańskim",
			"Urząd Skarbowy w Tczewie", "Urząd Skarbowy w Choszcznie", "Urząd Skarbowy w Międzychodzie",
			"Urząd Skarbowy w Międzyrzeczu", "Urząd Skarbowy w Myśliborzu", "Urząd Skarbowy w Słubicach",
			"Urząd Skarbowy w Gorzowie Wielkopolskim", "Urząd Skarbowy w Przysusze", "Urząd Skarbowy w Nisku",
			"Urząd Skarbowy w Hajnówce", "Urząd Skarbowy w Pruszczu Gdańskim", "Urząd Skarbowy w Kazimierzy Wielkiej",
			"Urząd Skarbowy w Obornikach", "Urząd Skarbowy w Chodzieży", "Urząd Skarbowy w Pleszewie",
			"Urząd Skarbowy we Włoszczowie", "Urząd Skarbowy w Jeleniej-Górze", "Urząd Skarbowy w Bolesławcu",
			"Urząd Skarbowy w Lubaniu", "Urząd Skarbowy w Kamiennej-Górze", "Urząd Skarbowy w Zgorzelcu",
			"Urząd Skarbowy w Lwówku ŚLąskim", "Urząd Skarbowy w Jarocinie", "Pierwszy Urząd Skarbowy w Kaliszu",
			"Urząd Skarbowy w Kępnie", "Urząd Skarbowy w Krotoszynie", "Urząd Skarbowy w Ostrowie Wielkopolskim",
			"Urząd Skarbowy w Piekarach Śląskich", "Drugi Urząd Skarbowy w Koszalinie", "Urząd Skarbowy w Będzinie",
			"Urząd Skarbowy w Bytomiu", "Urząd Skarbowy w Chorzowie", "Urząd Skarbowy w Chrzanowie",
			"Urząd Skarbowy w Dąbrowie Górniczej", "Pierwszy Urząd Skarbowy w Gliwicach", "Urząd Skarbowy w Jaworznie",
			"Urząd Skarbowy w Jastrzębiu-Zdroju", "Pierwszy Urząd Skarbowy w Katowicach", "Urząd Skarbowy w Mikołowie",
			"Urząd Skarbowy w Mysłowicach", "Urząd Skarbowy w Olkuszu", "Urząd Skarbowy w Pszczynie",
			"Urząd Skarbowy w Raciborzu", "Urząd Skarbowy w Rudzie Śląskiej", "Urząd Skarbowy w Rybniku",
			"Urząd Skarbowy w Siemianowicach Śląskich", "Urząd Skarbowy w Sosnowcu",
			"Urząd Skarbowy w Tarnowskich Górach", "Urząd Skarbowy w Tychach", "Urząd Skarbowy w Wodzisławiu Śląskim",
			"Urząd Skarbowy w Zabrzu", "Urząd Skarbowy w Zawierciu", "Urząd Skarbowy w Żorach",
			"Urząd Skarbowy w Czechowicach Dziedzicach", "Urząd Skarbowy Tarnowskie Góry filia Piekary Śląskie",
			"Filia US w Będzinie/Czeladź ", "Urząd Skarbowy w Busku-Zdroju", "Urząd Skarbowy w Jędrzejowie",
			"Pierwszy Urząd Skarbowy w Kielcach", "Urząd Skarbowy w Końskich", "Urząd Skarbowy w Miechowie",
			"Urząd Skarbowy w Ostrowcu Świętokrzyskim", "Urząd Skarbowy w Pińczowie",
			"Urząd Skarbowy w Skarżysku Kamiennej", "Urząd Skarbowy w Starachowicach", "Urząd Skarbowy w Koninie",
			"Urząd Skarbowy w Kole", "Urząd Skarbowy w Słupcy", "Urząd Skarbowy w Turku",
			"Pierwszy Urząd Skarbowy w Koszalinie", "Urząd Skarbowy w Kołobrzegu", "Urząd Skarbowy w Białogardzie",
			"Urząd Skarbowy w Szczecinku", "Urząd Skarbowy w Drawsku Pomorskim", "Urząd Skarbowy Kraków-Śródmieście",
			"Urząd Skarbowy Kraków-Stare Miasto", "Urząd Skarbowy Kraków-Krowodrza", "Urząd Skarbowy Kraków-Nowa Huta",
			"Urząd Skarbowy Kraków-Podgórze", "Urząd Skarbowy w Myślenicach", "Urząd Skarbowy w Proszowicach",
			"Urząd Skarbowy w Wieliczce", "Urząd Skarbowy w Krośnie", "Urząd Skarbowy w Jaśle",
			"Urząd Skarbowy w Brzozowie", "Urząd Skarbowy w Sanoku", "Urząd Skarbowy w Lesku",
			"Urząd Skarbowy w Ustrzykach Dolnych", "Urząd Skarbowy w Legnicy", "Urząd Skarbowy w Lubinie",
			"Urząd Skarbowy w Głogowie", "Urząd Skarbowy w Złotoryi", "Urząd Skarbowy w Jaworze",
			"Urząd Skarbowy w Gostyniu", "Urząd Skarbowy w Lesznie", "Urząd Skarbowy w Kościanie",
			"Urząd Skarbowy w Rawiczu", "Trzeci Urząd Skarbowy  Warszawa-Śródmieście", "Urząd Skarbowy w Lesznie",
			"Pierwszy Urząd Skarbowy w Lublinie", "Drugi Urząd Skarbowy w Lublinie", "Urząd Skarbowy w Lubartowie",
			"Urząd Skarbowy w Kraśniku", "Urząd Skarbowy w Puławach", "Urząd Skarbowy w Opolu Lubelskim",
			"Urząd Skarbowy w Łomży", "Urząd Skarbowy w Grajewie", "Urząd Skarbowy w Kolnie",
			"Urząd Skarbowy w Wysokiem Mazowieckiem", "Urząd Skarbowy w Zambrowie",
			"Pierwszy Urząd Skarbowy Łódź-Śródmieście", "Urząd Skarbowy Łódź-Śródmieście",
			"Pierwszy Urząd Skarbowy Łódź-Bałuty", "Urząd Skarbowy Łódź-Polesie", "Urząd Skarbowy Łódź-Widzew",
			"Pierwszy Urząd Skarbowy Łódź-Górna", "Urząd Skarbowy w Pabianicach", "Urząd Skarbowy w Zgierzu",
			"Urząd Skarbowy w Głownie", "Urząd Skarbowy w Nowym Sączu", "Urząd Skarbowy w Nowym Targu",
			"Urząd Skarbowy w Zakopanem", "Urząd Skarbowy w Limanowej", "Urząd Skarbowy w Gorlicach",
			"Urząd Skarbowy w Olsztynie", "Urząd Skarbowy w Ostródzie", "Urząd Skarbowy w Kętrzynie",
			"Urząd Skarbowy w Bartoszycach", "Urząd Skarbowy w Iławie", "Urząd Skarbowy w Szczytnie",
			"Filia US w Szczytnie/Nidzica", "Urząd Skarbowy w Brzegu", "Urząd Skarbowy w Głubczycach",
			"Urząd Skarbowy w Kędzierzynie-Koźlu", "Urząd Skarbowy w Kluczborku", "Urząd Skarbowy w Namysłowie",
			"Urząd Skarbowy w Nysie", "Pierwszy Urząd Skarbowy w Opolu", "Urząd Skarbowy w Prudniku",
			"Urząd Skarbowy w Strzelcach Opolskich", "Urząd Skarbowy w Makowie Mazowieckim",
			"Urząd Skarbowy w Ostrołęce", "Urząd Skarbowy w Ostrowi Mazowieckiej", "Urząd Skarbowy w Przasnyszu",
			"Urząd Skarbowy w Wyszkowie", "Urząd Skarbowy w Czarnkowie", "Urząd Skarbowy w Pile",
			"Urząd Skarbowy w Wałczu", "Urząd Skarbowy w Wągrowcu", "Urząd Skarbowy w Złotowie",
			"Urząd Skarbowy w Opocznie", "Urząd Skarbowy w Bełchatowie", "Urząd Skarbowy w Piotrkowie Trybunalskim",
			"Urząd Skarbowy w Radomsku", "Urząd Skarbowy w Tomaszowie Mazowieckim", "Urząd Skarbowy w Płocku",
			"Urząd Skarbowy w Kutnie", "Urząd Skarbowy w Sierpcu", "Pierwszy Urząd Skarbowy Poznań ",
			"Urząd Skarbowy Poznań-Śródmieście", "Urząd Skarbowy Poznań-Grunwald", "Urząd Skarbowy Poznań-Jeżyce",
			"Urząd Skarbowy Poznań-Nowe Miasto", "Urząd Skarbowy Poznań-Wilda", "Urząd Skarbowy w Gnieźnie",
			"Urząd Skarbowy w Śremie", "Urząd Skarbowy w Środzie Wielkopolskiej", "Urząd Skarbowy w Szamotułach",
			"Urząd Skarbowy w Nowym Tomyślu", "Urząd Skarbowy we Wrześni", "Filia US w Nowym Tomyślu/Grodzisk Wlkp.",
			"Urząd Skarbowy w Jarosławiu", "Urząd Skarbowy w Lubaczowie", "Urząd Skarbowy w Przeworsku",
			"Urząd Skarbowy w Przemyślu", "Pierwszy Urząd Skarbowy w Radomiu", "Urząd Skarbowy w Grójcu",
			"Urząd Skarbowy w Białobrzegach", "Urząd Skarbowy w Szydłowcu", "Urząd Skarbowy w Zwoleniu",
			"Urząd Skarbowy w Kozienicach", "Urząd Skarbowy w Rzeszowie", "Urząd Skarbowy w Kolbuszowej",
			"Urząd Skarbowy w Łańcucie", "Urząd Skarbowy w Leżajsku", "Urząd Skarbowy w Mielcu",
			"Urząd Skarbowy w Ropczycach", "Urząd Skarbowy w Strzyżowie", "Urząd Skarbowy w Siedlcach",
			"Urząd Skarbowy w Mińsku Mazowieckim", "Urząd Skarbowy w Sokołowie Podlaskim", "Urząd Skarbowy w Węgrowie",
			"Urząd Skarbowy w Łukowie", "Urząd Skarbowy w Garwolinie", "Urząd Skarbowy w Sieradzu",
			"Urząd Skarbowy w Poddębicach", "Urząd Skarbowy w Zduńskiej Woli", "Urząd Skarbowy w Łasku",
			"Urząd Skarbowy w Wieluniu", "Urząd Skarbowy w Brzezinach", "Urząd Skarbowy w Łowiczu",
			"Urząd Skarbowy w Rawie Mazowieckiej", "Urząd Skarbowy w Skierniewicach", "Urząd Skarbowy w Sochaczewie",
			"Urząd Skarbowy w Żyrardowie", "Urząd Skarbowy w Słupsku", "Urząd Skarbowy w Lęborku",
			"Urząd Skarbowy w Bytowie", "Urząd Skarbowy w Człuchowie", "Urząd Skarbowy w Suwałkach",
			"Urząd Skarbowy w Giżycku", "Urząd Skarbowy w Augustowie", "Urząd Skarbowy w Olecku",
			"Urząd Skarbowy w Ełku", "Urząd Skarbowy w Piszu", "Pierwszy Urząd Skarbowy w Szczecinie",
			"Drugi Urząd Skarbowy w Szczecinie", "Urząd Skarbowy w Pyrzycach",
			"Urząd Skarbowy w Starogardzie Szczecińskim", "Urząd Skarbowy w Świnoujściu", "Urząd Skarbowy w Goleniowie",
			"Urząd Skarbowy w Gryficach", "Urząd Skarbowy w Gryfinie", "Filia US w Goleniowie/Nowogard",
			"Filia US w Gryficach/Kamień Pom.", "Urząd Skarbowy w Janowie Lubelskim", "Urząd Skarbowy w Opatowie",
			"Urząd Skarbowy w Sandomierzu", "Urząd Skarbowy w Stalowej Woli", "Urząd Skarbowy w Staszowie",
			"Urząd Skarbowy w Tarnobrzegu", "Urząd Skarbowy w Bochni", "Urząd Skarbowy w Brzesku",
			"Urząd Skarbowy w Dąbrowie Tarnowskiej", "Urząd Skarbowy w Dębicy", "Pierwszy Urząd Skarbowy w Tarnowie",
			"Urząd Skarbowy w Brodnicy", "Urząd Skarbowy w Chełmnie", "Urząd Skarbowy w Grudziądzu",
			"Urząd Skarbowy w Nowym Mieście Lubawskim", "Urząd Skarbowy w Wąbrzeźnie", "Drugi Urząd Skarbowy w Toruniu",
			"Urząd Skarbowy w Bystrzycy Kłodzkiej", "Urząd Skarbowy w Dzierżoniowie", "Urząd Skarbowy w Kłodzku",
			"Urząd Skarbowy w Świdnicy", "Urząd Skarbowy w Nowej Rudzie", "Urząd Skarbowy w Wałbrzychu",
			"Urząd Skarbowy w Ząbkowicach Śląskich", "Urząd Skarbowy w Włocławku", "Urząd Skarbowy w Radziejowie",
			"Urząd Skarbowy w Aleksandrowie Kujawskim", "Urząd Skarbowy w Rypinie", "Urząd Skarbowy w Lipnie",
			"Urząd Skarbowy Wrocław-Fabryczna", "Urząd Skarbowy Wrocław-Psie Pole",
			"Pierwszy Urząd Skarbowy we Wrocławiu", "Urząd Skarbowy Wrocław-Stare Miasto",
			"Drugi Urząd Skarbowy Wrocław-Śródmieście", "Urząd Skarbowy Wrocław-Krzyki", "Urząd Skarbowy w Oleśnicy",
			"Urząd Skarbowy w Oławie", "Urząd Skarbowy w Środzie Wielkopolskiej", "Urząd Skarbowy w Strzelnie",
			"Urząd Skarbowy w Trzebnicy", "Urząd Skarbowy w Miliczu", "Filia US w Trzebnicy/Wołów",
			"Urząd Skarbowy w Biłgoraju", "Urząd Skarbowy w Hrubieszowie", "Urząd Skarbowy w Tomaszowie Lubelskim",
			"Urząd Skarbowy w Zamościu", "Urząd Skarbowy w Wolsztynie", "Urząd Skarbowy w Żaganiu",
			"Urząd Skarbowy w Nowej Soli", "Urząd Skarbowy w Krośnie Odrzańskim", "Urząd Skarbowy w Świebodzinie",
			"Urząd Skarbowy w Żarach", "Pierwszy Urząd Skarbowy w Zielonej Górze", "Urząd Skarbowy Warszawa-Ursynów",
			"Urząd Skarbowy Warszawa-Wawer", "Drugi Urząd Skarbowy w Bydgoszczy", "Drugi Urząd Skarbowy w Katowicach",
			"Trzeci Urząd Skarbowy w Szczecinie", "Pierwszy Urząd Skarbowy w Toruniu",
			"Drugi Urząd Skarbowy w Bielsku-Białej", "Drugi Urząd Skarbowy w Bielsku-Białej",
			"Drugi Urząd Skarbowy w Częstochowie", "Trzeci Urząd Skarbowy w Gdańsku", "Drugi Urząd Skarbowy w Gdyni",
			"Drugi Urząd Skarbowy w Kielcach", "Urząd Skarbowy Kraków-Dębniki", "Urząd Skarbowy Kraków-Prądnik",
			"Trzeci Urząd Skarbowy w Lublinie", "Drugi Urząd Skarbowy Łódź-Bałuty", "Drugi Urząd Skarbowy w Radomiu",
			"Drugi Urząd Skarbowy w Częstochowie", "Urząd Skarbowy Warszawa-Ursynów", "Urząd Skarbowy Warszawa-Wawer",
			"Drugi Urząd Skarbowy w Bydgoszczy", "Drugi Urząd Skarbowy w Katowicach",
			"Trzeci Urząd Skarbowy w Szczecinie", "Pierwszy Urząd Skarbowy w Toruniu",
			"Trzeci Urząd Skarbowy w Gdańsku", "Drugi Urząd Skarbowy w Gdyni", "Drugi Urząd Skarbowy w Kielcach",
			"Urząd Skarbowy Kraków-Dębniki", "Urząd Skarbowy Kraków-Prądnik", "Trzeci Urząd Skarbowy w Lublinie",
			"Drugi Urząd Skarbowy Łódź-Bałuty", "Drugi Urząd Skarbowy w Radomiu", "Drugi Urząd Skarbowy w Białymstoku",
			"Trzeci Urząd Skarbowy w Bydgoszczy", "Drugi Urząd Skarbowy w Kaliszu", "Drugi Urząd Skarbowy w Gliwicach",
			"Urząd Skarbowy w Gostyninie", "Urząd Skarbowy Poznań-Winogrady", "Drugi Urząd Skarbowy w Zielonej-Górze",
			"Drugi Urząd Skarbowy w Białymstoku", "Trzeci Urząd Skarbowy w Bydgoszczy",
			"Drugi Urząd Skarbowy w Bydgoszczy", "Drugi Urząd Skarbowy w Gliwicach", "Urząd Skarbowy w Gostyninie",
			"Urząd Skarbowy Poznań-Winogrady", "Drugi Urząd Skarbowy w Zielonej Górze",
			"Drugi Urząd Skarbowy Łódź-Górna", "Drugi Urząd Skarbowy Łódź-Górna", "Urząd Skarbowy w Nidzicy",
			"Urząd Skarbowy w Nidzicy", "Urząd Skarbowy w Kamieniu Pomorskim", "Urząd Skarbowy w Kamieniu Pomorskim",
			"Urząd Skarbowy w Wołowie", "Urząd Skarbowy w Wołowie", "Drugi Urząd Skarbowy w Opolu",
			"Drugi Urząd Skarbowy w Opolu", "Drugi Urząd Skarbowy w Tarnowie", "Drugi Urząd Skarbowy w Tarnowie",
			"Urząd Skarbowy w Grodzisku Wielkopolskim", "Urząd Skarbowy w Grodzisku Wielkopolskim",
			"Urząd Skarbowy Wieluń lokalizacja w Wieruszowie", "Urząd Skarbowy Wieluń lokalizacja w Wieruszowie" };

	@Override
	public void initialize(ValidNip validNip) {
		min = validNip.min();
	}

	@Override
	public boolean isValid(String nip, ConstraintValidatorContext context) {
		if (nip.length() != 10) {
			valid = false;
		} else {
			for (int i = 0; i < 10; i++) {
				NIP[i] = Byte.parseByte(nip.substring(i, i + 1));
			}
			if (checkSum()) {
				valid = true;
			} else {
				valid = false;
			}
		}
		return valid;
	}
	
	public static boolean isNipValid(String nip) {
		if (nip.length() != 10) {
			valid = false;
		} else {
			for (int i = 0; i < 10; i++) {
				NIP[i] = Byte.parseByte(nip.substring(i, i + 1));
			}
			if (checkSum()) {
				valid = true;
			} else {
				valid = false;
			}
		}
		return valid;
	}
	
	private static boolean checkSum() {
		int sum;
		sum = 6 * NIP[0] + 5 * NIP[1] + 7 * NIP[2] + 2 * NIP[3] + 3 * NIP[4] + 4 * NIP[5] + 5 * NIP[6] + 6 * NIP[7]
				+ 7 * NIP[8];
		sum %= 11;

		if (NIP[9] == sum)
			return true;
		else
			return false;
	}

	public String getDep() {
		int number;
		String result = "unknown";
		number = 100 * NIP[0];
		number += 10 * NIP[1];
		number += NIP[2];
		for (int i = 0; i < DepNo.length; i++) {
			if (DepNo[i] == number) {
				result = DepName[i];
				break;
			}
		}
		return result;
	}

}