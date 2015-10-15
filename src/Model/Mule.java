package Model;
import Model.MuleType;
/**
 * Created by Ashley on 9/30/2015.
 */
public class Mule extends Resources {
    private MuleType kind;
    private int cost;
    
    public Mule(MuleType kind) {
    this.kind = kind;
    cost = 10;
  }

  public MuleType getKind() {
    return kind;
  }

  public int getCost() {
    return cost;
  }
}