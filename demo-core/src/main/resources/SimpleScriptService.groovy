import com.roy.v8.bill.ScriptService
import com.roy.v8.bill.UserVo
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by BG244210 on 2016/7/7.
 */
class Simple implements ScriptService {


    public void run() {
        println(new Date());
    }
}

