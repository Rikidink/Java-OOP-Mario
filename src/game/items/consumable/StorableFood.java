package game.items.consumable;

import edu.monash.fit2099.engine.items.Item;

public abstract class StorableFood extends Item implements Consumable {

    /**
     * If the item has been consumed
     */
     private boolean consumptionStatus =  false;

    /**
     * Constructor
     * @param name
     * @param displayChar
     * @param portable
     */
    public StorableFood(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

     protected void setHasBeenConsumed(boolean consumptionStatus){
         this.consumptionStatus = consumptionStatus;
     }

     public boolean getConsumptionStatus(){
         return consumptionStatus;
     }

     public abstract String getMenuDescriptionText();

}
