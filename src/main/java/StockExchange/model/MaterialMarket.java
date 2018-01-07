
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jakub
 */
public class MaterialMarket extends Exchange implements DisplayableListItem {
    private String name;
    private List<Material> materialList = new ArrayList<>();

    @Override
    public String getDisplayName() {
        return name;
    }

    
    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * @param materialList
     */
    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }

    /**
     *
     * @return materialList
     */
    public List<Material> getMaterialList() {
        return materialList;
    }
    
    
    
}
