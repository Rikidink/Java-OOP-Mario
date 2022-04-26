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
     *
     * @param value The value of a Coin that has been picked up
     */
    public void addToWallet(Integer value) {
        walletValue += value;
    }

    public void removeFromWallet(Integer value) {
        walletValue -= value;
    }

    public Integer getWalletValue() {
        return walletValue;
    }
}
