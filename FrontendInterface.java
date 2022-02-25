import java.util.List;
import java.util.NoSuchElementException;

public interface FrontendInterface {
  public boolean insert(Hurricane hurricane);
  public boolean contains(String name);
  public List<HurricaneInterface> getThreeHurricane(String name) throws NoSuchElementException;
  public Hurricane lookup(String name) throws NoSuchElementException;
}
