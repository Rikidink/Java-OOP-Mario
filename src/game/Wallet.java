package game;

public class Wallet {

    private Integer walletValue;

    /**
     * A singleton wallet instance
     */
    private static Wallet instance;

    /**
     * Get the singleton instance of wallet
     * @return Wallet singleton instance
     */
    public static Wallet getInstance(){
        if(instance == null){
            instance = new Wallet();
        }
        return instance;
    }

    /**
     * Constructor
     */
    public Wallet () {
        walletValue = 0;
    }

    /**
     *Adds a value to the total money in the Wallet
     * @param value The value of a Coin that has been picked up
     */
    public void addToWallet(Integer value) {
        walletValue += value;
    }

    /**
     * Removes a value from the total money in the Wallet
     * @param value The value of an Item that is being bought
     */
    public void removeFromWallet(Integer value) {
        walletValue -= value;
    }

    /**
     * Getter for the current value of the Wallet
     * @return  The current total money stored in the wallet
     */
    public Integer getWalletValue() {
        return walletValue;
    }
}
