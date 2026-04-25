//package ch.hslu.w7_Aktoren;
//
//public class AuctioActor extends Actor{
//
//    private int currentHighestBid;
//    private Actor<AuctionWonMessage> highestBidder;
//    private boolean isFinished = false;
//
//    public AuctioActor(int minBid, long durationMS){
//        this.currentHighestBid = minBid;
//        TimeOutActor timer = new TimeOutActor();
//        timer.tell(new Timeout);
//    }
//    @Override
//    protected void onMessage(Object message) throws Exception {
//
//    }
//}
