package livelihoodzone.common;

import livelihoodzone.dto.reports.wealthgroup.CropContributionAspectDto;
import livelihoodzone.dto.reports.zonal.HazardAspectObject;
import livelihoodzone.dto.reports.zonal.SeasonTypesObject;

public class Constants {

    //Questionnaire types
    public static final int ZONE_LEVEL_QUESTIONNAIRE = 1;
    public static final int WEALTH_GROUP_QUESTIONNAIRE = 2;

    //Wealth group questionnaire types
    public static final int WEALTH_GROUP_SUMMARISED_QUESTIONNAIRE_TYPE_CODE = 1;
    public static final int WEALTH_GROUP_RAW_DATA_QUESTIONNAIRE_TYPE_CODE = 2;

    //Wealth Group Questionnaire Section Codes
    public static final int MAIN_SOURCE_OF_INCOME_AND_FOOD = 1;
    public static final int PERCENTAGE_FOOD_CONSUMPTION = 2;
    public static final int CROP_PRODUCTION = 3;
    public static final int LIVESTOCK_AND_POULTRY_OWNERSHIP = 4;
    public static final int LABOUR_PATTERNS = 5;
    public static final int EXPENDITURE_PATTERNS = 6;
    public static final int MIGRATION_PATTERNS = 7;
    public static final int ECONOMIC_ACTIVITY_CONSTRAINTS = 8;
    public static final int FGD_PARTICIPANTS = 9;
    public static final int LIVESTOCK_AND_POULTRY_CONTRIBUTION = 10;


    //Zone Level Questionnaire Section Codes
    public static final int WEALTH_GROUP_CHARACTERISTICS = 1;
    public static final int WEALTH_GROUP_POPULATION_PERCENTAGE = 2;
    public static final int LZ_CROP_PRODUCTION = 3;
    public static final int MAIN_SOURCES_OF_WATER = 4;
    public static final int MARKETS_SERVING_THE_LIVELIHOOD_ZONE = 5;
    public static final int SOCIETY_AND_ETHNICITY = 6;
    public static final int PATTERNS_OF_HUNGER = 7;
    public static final int HAZARDS = 8;
    public static final int SEASONAL_CALENDAR = 9;
    public static final int SC_SEASON_MONTHS = 10;
    public static final int SC_LAND_PREPARATION = 11;
    public static final int SC_PLANTING = 12;
    public static final int SC_HARVESTING = 13;
    public static final int SC_LIVESTOCK_MIGRATION = 14;
    public static final int SC_MILK_PRODUCTION = 15;
    public static final int SC_CALVING = 16;
    public static final int SC_KIDDING = 17;
    public static final int SC_FOOD_PRICES = 18;
    public static final int SC_LIVESTOCK_PRICES = 19;
    public static final int SC_AGRICULTURE_CASUAL_LABOUR_AVAILABILITY = 20;
    public static final int SC_NON_AGRICULTURE_CASUAL_LABOUR_AVAILABILITY = 21;
    public static final int SC_CASUAL_LABOUR_WAGES = 22;
    public static final int SC_REMITTANCES = 23;
    public static final int SC_FISHING = 24;
    public static final int SC_MARKET_ACCESS = 25;
    public static final int SC_DISEASE_OUTBREAK = 26;
    public static final int SC_WATER_STRESS = 27;
    public static final int SC_CONFLICT_RISKS = 28;
    public static final int SC_CEREMONIES = 29;
    public static final int SC_LEAN_SEASONS = 30;
    public static final int SC_FOOD_SECURITY_ASSESSMENT = 31;




    //General boundaries
    public static final int GENERAL_COUNTY_ID = 54;
    public static final int GENERAL_SUBCOUNTY_ID = 407;
    public static final int GENERAL_WARD_ID = 1516;
    public static final int GENERAL_SUBLOCATION_ID = 7597;


    //Role codes
    public static final int ADMIN = 1;
    public static final int WEALTH_GROUP_RAW_DATA_COLLECTOR = 2;
    public static final int DATA_ANALYST = 3;
    public static final int WEALTH_GROUP_SUMMARISED_DATA_COLLECTOR = 4;
    public static final int ZONE_LEVEL_DATA_COLLECTOR = 5;
    public static final int COUNTY_SUPERVISOR = 6;

    //Wealth Group Codes
    public static final int VERY_POOR_CODE = 1;
    public static final int POOR_CODE = 2;
    public static final int MEDIUM_CODE = 3;
    public static final int BETTER_OFF_CODE = 4;

    //Rainy Season Codes
    public static final int LONG_RAINS_SEASON = 1;
    public static final int SHORT_RAINS_SEASON = 2;
    public static final int BETWEEN_END_LONG_AND_BEGIN_SHORT = 3;
    public static final int BETWEEN_END_SHORT_AND_BEGIN_LONG = 4;

    //Crop Water-Access Codes
    public static final int RAINFED_CROPS = 1;
    public static final int IRRIGATED_CROPS = 2;

    //Water source codes
    public static final int RIVERS = 1;
    public static final int TRADITIONAL_RIVERS = 2;
    public static final int NATURAL_PONDS = 3;
    public static final int PANS_AND_DAMS = 4;
    public static final int SHALLOW_WELLS = 5;
    public static final int BOREHOLES = 6;
    public static final int SPRINGS = 7;
    public static final int LAKES = 8;
    public static final int ROCK_CATCHMENT = 9;
    public static final int PIPED_WATER = 10;
    public static final int WATER_TRUCKING = 11;
    public static final int ROOF_CATCHMENTS = 12;
    public static final int OTHERS = 13;

    //Income Sources Codes
    public static final int LIVESTOCK_PRODUCTION = 1;
    public static final int POULTRY_PRODUCTION = 2;
    public static final int PASTURE_FODDER_PRODUCTION = 17;
    public static final int CASH_CROP_PRODUCTION = 3;
    public static final int FOOD_CROP_PRODUCTION = 4;
    public static final int CASUAL_WAGED_LABOUR_INCOME = 5;
    public static final int FORMAL_WAGED_LABOUR = 6;
    public static final int FISHING = 7;
    public static final int HUNTING_AND_GATHERING = 8;
    public static final int SMALL_BUSINESSES = 9;
    public static final int FIREWOOD_COLLECTION = 10;
    public static final int PETTY_TRADING = 11;
    public static final int REMITTANCE_AND_GIFTS = 12;
    public static final int BODABODA_TRANSPORT = 13;
    public static final int BEE_KEEPING = 14;
    public static final int SAND_HARVESTING = 15;
    public static final int OTHERS_INCOME_SOURCES = 16;

    //Food Sources Codes
    public static final int OWN_FARM_PRODUCTION = 1;
    public static final int MARKET_FOOD_PURCHASE = 2;
    public static final int HUNTING_GATHERING_FISHING_FOOD_SOURCE = 3;
    public static final int GIFTS_AND_FOOD_AID = 4;

    //Food Types Codes
    public static final int MAIZE_AND_POSHO = 1;
    public static final int WHEAT_BARLEY_RYE = 2;
    public static final int SORGHUM_MILLET_PRODUCTS = 3;
    public static final int RICE_AND_PRODUCTS = 4;
    public static final int BEANS = 5;
    public static final int OTHER_PULSES = 6;
    public static final int VEGETABLES = 7;
    public static final int FRUITS_AND_BERRIES = 8;
    public static final int WHITE_ROOTS_TUBERS = 9;
    public static final int MEAT = 10;
    public static final int MILKS_AND_DAIRY_PRODUCTS = 11;
    public static final int FISH_AND_SEA_FOOD = 12;
    public static final int EGGS = 13;
    public static final int COOKING_FATS_AND_OILS = 14;
    public static final int SPICES_CONDIMENTS_BEVERAGES = 15;


    //Animal Codes
    public static final int LOCAL_CATTLE = 1;
    public static final int GOATS = 2;
    public static final int SHEEP = 3;
    public static final int DONKEYS = 4;
    public static final int CAMELS = 5;
    public static final int PIGS = 6;
    public static final int LOCAL_CHICKEN = 7;
    public static final int DUCKS = 8;
    public static final int BEE_HIVES = 9;
    public static final int FISH_PONDS = 10;
    public static final int IMPROVED_CATTLE = 11;
    public static final int IMPROVED_CHICKEN = 12;
    public static final int FISH_CAGES = 13;
    public static final int DAIRY_CATTLE = 14;
    public static final int TLU = 0;


    //Livelihood zone activities
    public static final int LABOUR_OWN_FARM = 1;
    public static final int LIVESTOCK_HUSBANDRY = 2;
    public static final int TRANSPORT_SERVICES = 15;
    public static final int WAGED_LABOUR = 3;
    public static final int LOW_SKILLED_NON_FARM_LABOUR = 4;
    public static final int SKILLED_LABOUR = 5;
    public static final int FORMAL_EMPLOYMENT = 6;
    public static final int HUNTING_AND_GATHERING_LZ_ACTIVITY = 7;
    public static final int FISHING_LZ_ACTIVITY = 8;
    public static final int TRADING_LZ_ACTIVITY = 9;
    public static final int DOMESTIC_UNPAID_WORK = 10;
    public static final int BEGGING_LZ_ACTIVITY = 11;
    public static final int COMMERCIAL_SEX_WORK = 12;
    public static final int LEISURE_SOCIALIZING_ENTERTAINMENT = 13;
    public static final int OTHER_LIVELIHOOD_ACTIVITIES = 14;


    //Expenditure items
    public static final int EXP_MAIZE_AND_MAIZE_FLOUR = 1;
    public static final int EXP_OTHER_CEREALS = 2;
    public static final int EXP_PULSES = 3;
    public static final int EXP_ROOTS_AND_ROOT_TUBERS = 4;
    public static final int EXP_VEGETABLES_AND_FRUITS = 5;
    public static final int EXP_FISH_AND_SEA_FOOD = 6;
    public static final int EXP_MEAT = 7;
    public static final int EXP_MILK = 8;
    public static final int EXP_EGGS = 9;
    public static final int EXP_OILS_AND_FATS = 10;
    public static final int EXP_OTHER_FOODS = 25;
    public static final int EXP_SCHOOL_FEES = 11;
    public static final int EXP_DRUGS_AND_MEDICAL_CARE = 12;
    public static final int EXP_CLOTHING_BEAUTY_PRODUCTS = 13;
    public static final int EXP_HOUSE_RENT = 14;
    public static final int EXP_COMMUNICATION_EXPENSES = 15;
    public static final int EXP_FARM_INPUTS = 16;
    public static final int EXP_LIVESTOCK_DRUGS = 26;
    public static final int EXP_PURCHASE_OF_WATER = 27;
    public static final int EXP_SOAPS_AND_OTHER_DETERGENTS = 28;
    public static final int EXP_HIRINNG_FARM_LABOUR = 29;
    public static final int EXP_TRAVEL_EXPENSES = 17;
    public static final int EXP_LEISURE_AND_ENTERTAINMENT = 18;
    public static final int EXP_ELECTRICITY_BILLS = 19;
    public static final int EXP_SOCIAL_OBLIGATION = 20;
    public static final int EXP_MILLING_COSTS = 21;
    public static final int EXP_COOKING_FUEL = 22;
    public static final int EXP_SAVING_AND_INVESTMENTS = 23;
    public static final int EXP_LOAN_REPAYMENTS = 24;


    //Migration patterns codes
    public static final int MGR_FULLY_NOMADIC = 1;
    public static final int MGR_SEMI_NOMADIC = 2;
    public static final int MGR_OCCASIONAL_NOMADIC = 3;
    public static final int MGR_OUT_MIGRANT_LABOUR = 4;
    public static final int MGR_IN_MIGRANT_LABOUR = 5;
    public static final int MGR_FULLY_SETTLED = 6;
    public static final int MGR_INTERNALLY_DISPLACED = 7;

    //Constraints income sources
    public static final int CONIN_WAGED_LABOUR = 1;
    public static final int CONIN_CROP_PRODUCTION = 2;
    public static final int CONIN_LIVESTOCK_PRODUCTION = 3;
    public static final int CONIN_FISHING = 4;
    public static final int CONIN_NATURAL_RESOURCES = 5;
    public static final int CONIN_SMALL_ENTERPRISE = 6;


    //Income constraints
    public static final int INCO_WL_LOW_EDUCATION = 1;
    public static final int INCO_WL_POOR_HEALTH = 2;
    public static final int INCO_WL_TOO_FEW_JOBS = 3;
    public static final int INCO_WL_TOO_MUCH_FARM_TIME = 4;
    public static final int INCO_WL_LOW_AVERAGE_WAGE_RATES = 5;


    public static final int INCO_CP_SMALL_LAND_HOLDINGS = 6;
    public static final int INCO_CP_LACK_OF_CREDIT = 7;
    public static final int INCO_CP_HIGH_INPUT_COSTS = 8;
    public static final int INCO_CP_LOW_LAND_FERTILITY = 9;
    public static final int INCO_CP_LACK_OF_RELIABLE_WATER = 10;
    public static final int INCO_CP_LOW_TECHNICAL_SKILLS = 11;
    public static final int INCO_CP_LOW_QUALITY_SEED_STOCK = 12;
    public static final int INCO_CP_LACK_MARKET_ACCESS = 13;
    public static final int INCO_CP_ENDEMIC_CROP_PESTS_DISEASES = 14;
    public static final int INCO_CP_LACK_OF_AGRICULTURAL_EXTENSION_SERVICES = 37;

    public static final int INCO_LP_LACK_OF_PASTURE = 15;
    public static final int INCO_LP_LACK_OF_ANIMAL_DRINKING_WATER = 16;
    public static final int INCO_LP_LOW_YIELDING_ANIMALS = 17;
    public static final int INCO_LP_HIGH_COST_VETERINARY_DRUGS = 18;
    public static final int INCO_LP_ENDEMIC_LIVESTOCK_PESTS_DISEASES = 19;
    public static final int INCO_LP_LACK_OF_MARKET = 20;
    public static final int INCO_LP_INSECURITY = 21;
    public static final int INCO_LP_LOW_TECHNICAL_SKILLS_KNOWLEDGE = 38;
    public static final int INCO_LP_UNFAVOURABLE_CLIMATE = 39;
    public static final int INCO_LP_LACK_OF_LIVESTOCK_EXTENSION_SERVICES = 40;

    public static final int INCO_FI_LOW_FISH_STOCKS = 22;
    public static final int INCO_FI_LOW_FISH_PRICE = 23;
    public static final int INCO_FI_LACK_OF_EQUIPMENT = 24;
    public static final int INCO_FI_TOO_MUCH_COMPETITION = 25;
    public static final int INCO_FI_LACK_OF_EXPERTISE = 26;
    public static final int INCO_FI_RESTRICTIONS_ON_FISHING_RIGHTS = 27;
    public static final int INCO_FI_INADEQUATE_COLD_STORAGE_FACILITIES = 41;

    public static final int INCO_NR_DECLINING_NATURAL_RESOURCES = 28;
    public static final int INCO_NR_TOO_MUCH_POPULATION_PRESSURE = 29;
    public static final int INCO_NR_RESTRICTIONS_RIGHTS_TO_EXPLOIT_NR = 30;
    public static final int INCO_NR_LOW_VALUE_NR_BASED_PRODUCTS = 31;

    public static final int INCO_SE_LACK_OF_CAPITAL = 32;
    public static final int INCO_SE_TOO_MUCH_RED_TAPE = 33;
    public static final int INCO_SE_TOO_MANY_TAXES = 34;
    public static final int INCO_SE_LACK_OF_ACCESS_TO_MARKETS = 35;
    public static final int INCO_SE_LACK_OF_EXPERTISE = 36;


    //Livelihood zone hazards codes
    public static final int HZ_ANIMAL_RUSTLING = 1;
    public static final int HZ_BANDITRY = 2;
    public static final int HZ_TERRORISM = 3;
    public static final int HZ_ETHNIC_CONFLICT = 4;
    public static final int HZ_POLITICAL_CONFLICT = 5;
    public static final int HZ_DROUGHT = 6;
    public static final int HZ_LIVESTOCK_PESTS_DISEASES = 7;
    public static final int HZ_HAILSTORMS_OR_FROST = 8;
    public static final int HZ_FLOODING = 9;
    public static final int HZ_LANDSLIDES = 10;
    public static final int HZ_HIGH_WINDS = 11;
    public static final int HZ_BUSH_FIRES = 12;
    public static final int HZ_CROP_PESTS = 13;
    public static final int HZ_LOCUST_INVASION = 14;
    public static final int HZ_CROP_DISEASES = 15;
    public static final int HZ_TERMINAL_ILLNESS = 16;
    public static final int HZ_MALARIA_OUTBREAK = 17;
    public static final int HZ_WATER_BORNE_DISEASE = 18;
    public static final int HZ_HUMAN_WILDLIFE_CONFLICT = 19;
    public static final int HZ_HIGH_FOOD_PRICES = 20;
    public static final int HZ_SHORTAGE_OF_FOOD_ON_MARKET = 21;
    public static final int HZ_DRINKING_WATER_SHORTAGES = 22;
    public static final int HZ_INVASIVE_PLANT_SPECIES = 23;
    public static final int HZ_OTHERS = 24;

    //Seasonal calendar seasons
    public static final int SC_DRY_SEASON = 1;
    public static final int SC_LONG_RAINS = 2;
    public static final int SC_SHORT_RAINS = 3;

    //Livestock migration
    public static final int LM_IN_MIGRATION = 1;
    public static final int LM_OUT_MIGRATION = 2;

    //High low medium scale codes
    public static final int HIGH = 1;
    public static final int MEDIUM = 2;
    public static final int LOW = 3;

    public static final SeasonTypesObject DRY_SEASON = new SeasonTypesObject(1, "Dry Season");
    public static final SeasonTypesObject WET_SEASON = new SeasonTypesObject(2, "Wet Season");

    public static final HazardAspectObject RANK_OF_IMPORTANCE = new HazardAspectObject(1,"Rank of Importance");
    public static final HazardAspectObject NO_OF_YEARS = new HazardAspectObject(2,"No of years(In the last 10 years)");


    public static final CropContributionAspectDto CASH_INCOME_RANK = new CropContributionAspectDto(1,"Cash Income Rank");
    public static final CropContributionAspectDto CASH_INCOME_APPROX_PERCENTAGE = new CropContributionAspectDto(2,"Cash Income Approx Percentage");
    public static final CropContributionAspectDto FOOD_CONSUMPTION_RANK = new CropContributionAspectDto(3,"Food Consumption Rank");
    public static final CropContributionAspectDto FOOD_CONSUMPTION_APPROX_PERCENTAGE = new CropContributionAspectDto(4,"Food Consumption Approx Percentage");
}
