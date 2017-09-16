package network;

/**
 * Created by omarfaroque on 9/15/17.
 */

public class SuperInteractor extends Interactor {

    public IRespayNetworkService NetworkService;
    public SuperInteractor(){
        NetworkService = super.createService();
    }

}
