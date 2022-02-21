package dailyWords;

import java.util.List;
import java.util.Set;

public interface GetDailyWords {
    List<String> getDailyWords(Set<String> letters);
}
