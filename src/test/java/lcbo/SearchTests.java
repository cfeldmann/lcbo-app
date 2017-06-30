package lcbo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lcbo.search.SearchResult;
import org.hamcrest.CustomMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SearchTests {

    private static final String EXPECTED_JSON_RESULT = "{\"status\":200,\"message\":null,\"pager\":{\"" +
            "records_per_page\":20,\"total_record_count\":6,\"current_page_record_count\":6,\"" +
            "is_first_page\":true,\"is_final_page\":true,\"current_page\":1,\"current_page_path\":\"/stores?access_key=" +
            "MDoyOTliYzRmMi00ZTYwLTExZTctYmM1YS0wMzBmNmMyNjI1NDI6ZzJuTUl2cWNlMFYwa0tDcWFkdkNhdlZreFNYbm9aQkkyQkVW\\u0026q=windsor\\u0026page=1\"," +
            "\"next_page\":null,\"next_page_path\":null,\"previous_page\":null,\"previous_page_path\":null," +
            "\"total_pages\":1,\"total_pages_path\":" +
            "\"/stores?access_key=MDoyOTliYzRmMi00ZTYwLTExZTctYmM1YS0wMzBmNmMyNjI1NDI6ZzJuTUl2cWNlMFYwa0tDcWFkdkNhdlZreFNYbm9aQkkyQkVW\\u0026q=windsor\\u0026page=1\"}," +
            "\"result\":[{\"id\":367,\"is_dead\":false,\"name\":\"Howard \\u0026 E.C. Row Expwy\"," +
            "\"tags\":\"howard ec row expwy 3165 avenue the roundhouse centre windsor n8x3y9\"," +
            "\"address_line_1\":\"3165 Howard Avenue\",\"address_line_2\":\"The Roundhouse Centre\",\"city\":\"Windsor\"," +
            "\"postal_code\":\"N8X3Y9\",\"telephone\":\"(519) 972-1772\",\"fax\":\"(519) 250-1206\",\"latitude\":42.2742," +
            "\"longitude\":-83.0058,\"products_count\":3939,\"inventory_count\":116731,\"inventory_price_in_cents\":213400617," +
            "\"inventory_volume_in_milliliters\":96973134,\"has_wheelchair_accessability\":true,\"has_bilingual_services\":true," +
            "\"has_product_consultant\":true,\"has_tasting_bar\":true,\"has_beer_cold_room\":false,\"has_special_occasion_permits\":true," +
            "\"has_vintages_corner\":true,\"has_parking\":true,\"has_transit_access\":true,\"sunday_open\":600,\"sunday_close\":1080," +
            "\"monday_open\":600,\"monday_close\":1320,\"tuesday_open\":600,\"tuesday_close\":1320,\"wednesday_open\":600," +
            "\"wednesday_close\":1320,\"thursday_open\":600,\"thursday_close\":1320,\"friday_open\":600,\"friday_close\":1320," +
            "\"saturday_open\":null,\"saturday_close\":null,\"updated_at\":\"2017-06-30T14:15:51.694Z\",\"store_no\":367}," +
            "{\"id\":490,\"is_dead\":false,\"name\":\"Tecumseh \\u0026 Lauzon\",\"tags\":\"tecumseh lauzon 7640 rd e windsor n8t1e9\"," +
            "\"address_line_1\":\"7640 Tecumseh Rd. E.\",\"address_line_2\":null,\"city\":\"Windsor\",\"postal_code\":\"N8T1E9\"," +
            "\"telephone\":\"(519) 944-4014\",\"fax\":\"(519) 944-3527\",\"latitude\":42.3153,\"longitude\":-82.9388," +
            "\"products_count\":3513,\"inventory_count\":94003,\"inventory_price_in_cents\":149810438,\"inventory_volume_in_milliliters\":75913686," +
            "\"has_wheelchair_accessability\":true,\"has_bilingual_services\":true,\"has_product_consultant\":true,\"has_tasting_bar\":true," +
            "\"has_beer_cold_room\":true,\"has_special_occasion_permits\":true,\"has_vintages_corner\":true,\"has_parking\":true," +
            "\"has_transit_access\":true,\"sunday_open\":600,\"sunday_close\":1080,\"monday_open\":600,\"monday_close\":1260,\"tuesday_open\":600," +
            "\"tuesday_close\":1260,\"wednesday_open\":600,\"wednesday_close\":1260,\"thursday_open\":600,\"thursday_close\":1320,\"friday_open\":600," +
            "\"friday_close\":1320,\"saturday_open\":null,\"saturday_close\":null,\"updated_at\":\"2017-06-30T14:15:51.386Z\",\"store_no\":490}," +
            "{\"id\":371,\"is_dead\":false,\"name\":\"Southdown \\u0026 Royal Windsor\",\"tags\":\"southdown royal windsor 930 road mississauga l5j2y4\"," +
            "\"address_line_1\":\"930 Southdown Road\",\"address_line_2\":null,\"city\":\"Mississauga\",\"postal_code\":\"L5J2Y4\"," +
            "\"telephone\":\"(905) 822-2281\",\"fax\":\"(905) 823-2676\",\"latitude\":43.5081,\"longitude\":-79.6325,\"products_count\":3162," +
            "\"inventory_count\":85909,\"inventory_price_in_cents\":133822002,\"inventory_volume_in_milliliters\":69122668," +
            "\"has_wheelchair_accessability\":true,\"has_bilingual_services\":true,\"has_product_consultant\":true,\"has_tasting_bar\":false," +
            "\"has_beer_cold_room\":false,\"has_special_occasion_permits\":true,\"has_vintages_corner\":true,\"has_parking\":true," +
            "\"has_transit_access\":true,\"sunday_open\":540,\"sunday_close\":1200,\"monday_open\":600,\"monday_close\":1260," +
            "\"tuesday_open\":600,\"tuesday_close\":1260,\"wednesday_open\":600,\"wednesday_close\":1260,\"thursday_open\":600," +
            "\"thursday_close\":1260,\"friday_open\":600,\"friday_close\":1260,\"saturday_open\":null,\"saturday_close\":null," +
            "\"updated_at\":\"2017-06-30T14:15:21.157Z\",\"store_no\":371},{\"id\":539,\"is_dead\":false,\"name\":\"Tecumseh \\u0026 Huron Line\"," +
            "\"tags\":\"tecumseh huron line 1550 unit 4 church road ambassador plaza windsor n9c3z3\",\"address_line_1\":\"1550 Unit 4 Huron Church Road\"," +
            "\"address_line_2\":\"Ambassador Plaza\",\"city\":\"Windsor\",\"postal_code\":\"N9C3Z3\",\"telephone\":\"(519) 256-5335\"," +
            "\"fax\":\"(519) 256-8931\",\"latitude\":42.2878,\"longitude\":-83.0573,\"products_count\":2233,\"inventory_count\":53995," +
            "\"inventory_price_in_cents\":68439454,\"inventory_volume_in_milliliters\":43032669,\"has_wheelchair_accessability\":true," +
            "\"has_bilingual_services\":true,\"has_product_consultant\":false,\"has_tasting_bar\":true,\"has_beer_cold_room\":true," +
            "\"has_special_occasion_permits\":true,\"has_vintages_corner\":true,\"has_parking\":true,\"has_transit_access\":true," +
            "\"sunday_open\":600,\"sunday_close\":1080,\"monday_open\":600,\"monday_close\":1260,\"tuesday_open\":600," +
            "\"tuesday_close\":1260,\"wednesday_open\":600,\"wednesday_close\":1260,\"thursday_open\":600,\"thursday_close\":1260," +
            "\"friday_open\":600,\"friday_close\":1260,\"saturday_open\":null,\"saturday_close\":null,\"updated_at\":\"2017-06-30T14:15:51.909Z\"," +
            "\"store_no\":539},{\"id\":34,\"is_dead\":false,\"name\":\"Walker \\u0026 Ottawa\",\"tags\":\"walker ottawa 1320 road windsor n8y4t9\"," +
            "\"address_line_1\":\"1320 Walker Road\",\"address_line_2\":null,\"city\":\"Windsor\",\"postal_code\":\"N8Y4T9\"," +
            "\"telephone\":\"(519) 254-1543\",\"fax\":\"(519) 254-1291\",\"latitude\":42.3136,\"longitude\":-83.0012," +
            "\"products_count\":2087,\"inventory_count\":52559,\"inventory_price_in_cents\":74605242,\"inventory_volume_in_milliliters\":42316563," +
            "\"has_wheelchair_accessability\":true,\"has_bilingual_services\":true,\"has_product_consultant\":false,\"has_tasting_bar\":true," +
            "\"has_beer_cold_room\":false,\"has_special_occasion_permits\":true,\"has_vintages_corner\":false,\"has_parking\":true," +
            "\"has_transit_access\":true,\"sunday_open\":600,\"sunday_close\":1080,\"monday_open\":600,\"monday_close\":1260," +
            "\"tuesday_open\":600,\"tuesday_close\":1260,\"wednesday_open\":600,\"wednesday_close\":1260,\"thursday_open\":600," +
            "\"thursday_close\":1260,\"friday_open\":600,\"friday_close\":1260,\"saturday_open\":null,\"saturday_close\":null," +
            "\"updated_at\":\"2017-06-30T14:15:51.615Z\",\"store_no\":34},{\"id\":32,\"is_dead\":false,\"name\":\"University \\u0026 Church\"," +
            "\"tags\":\"university church 400 avenue west windsor n9a5p8\",\"address_line_1\":\"400 University Avenue West\",\"address_line_2\":null," +
            "\"city\":\"Windsor\",\"postal_code\":\"N9A5P8\",\"telephone\":\"(519) 253-2221\",\"fax\":\"(519) 253-2029\",\"latitude\":42.3166," +
            "\"longitude\":-83.0434,\"products_count\":1594,\"inventory_count\":45678,\"inventory_price_in_cents\":56071752," +
            "\"inventory_volume_in_milliliters\":34062490,\"has_wheelchair_accessability\":true,\"has_bilingual_services\":true," +
            "\"has_product_consultant\":false,\"has_tasting_bar\":false,\"has_beer_cold_room\":false,\"has_special_occasion_permits\":true," +
            "\"has_vintages_corner\":false,\"has_parking\":true,\"has_transit_access\":true,\"sunday_open\":600,\"sunday_close\":1080," +
            "\"monday_open\":600,\"monday_close\":1320,\"tuesday_open\":600,\"tuesday_close\":1320,\"wednesday_open\":600," +
            "\"wednesday_close\":1320,\"thursday_open\":600,\"thursday_close\":1320,\"friday_open\":600,\"friday_close\":1320," +
            "\"saturday_open\":null,\"saturday_close\":null,\"updated_at\":\"2017-06-30T14:15:51.770Z\",\"store_no\":32}]}";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void accessSecuredResourceAuthenticatedThenOk() throws Exception {
        mockMvc.perform(get("/results?keyword=windsor"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("searchResult",
                        new CustomMatcher <String>("a json string") {
                            public boolean matches(Object searchResult) {
                                ObjectMapper mapper = new ObjectMapper();
                                try {
                                    return mapper.readValue(EXPECTED_JSON_RESULT, SearchResult.class)
                                            .equals(searchResult);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return false;
                            }
                        }
                ));
    }
}
