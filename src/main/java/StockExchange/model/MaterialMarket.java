
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;
import StockExchange.util.RandomString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jakub
 */
public class MaterialMarket extends Exchange implements DisplayableListItem, Serializable {
    private String name;
    private List<Material> materialList;

    public MaterialMarket() {
        super();
        name = RandomString.nextString(10);
        materialList = new ArrayList<>();
        Random generator = new Random();
        List<Material> materials = ApplicationModel.getInstance().getMaterials();
        for(Material material: materials) {
            if(generator.nextInt(2) == 1) {
                materialList.add(material);
            }
        }
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     *
     * @return materialList
     */
    public List<Material> getMaterialList() {
        return materialList;
    }
    
    
    
}
