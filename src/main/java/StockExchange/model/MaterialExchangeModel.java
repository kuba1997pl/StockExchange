
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jakub
 */
public class MaterialExchangeModel extends Exchange implements DisplayableListItem {
    private String name;
    private List<MaterialModel> materialList = new ArrayList<>();

    @Override
    public String getDisplayName() {
        return name;
    }


    @Override
    public int countMargin(){ return 0; }
    
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
    public void setMaterialList(List<MaterialModel> materialList) {
        this.materialList = materialList;
    }

    /**
     *
     * @return materialList
     */
    public List<MaterialModel> getMaterialList() {
        return materialList;
    }
    
    
    
}
