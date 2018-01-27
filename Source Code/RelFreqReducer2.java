import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.*;
import java.util.*;

public class RelFreqReducer2 extends Reducer<DoubleWritable, AdjacentWord, AdjacentWord, DoubleWritable>
{
    private int i = 0;
	
    @Override
    protected void reduce(DoubleWritable key, Iterable<AdjacentWord> values, Context context) throws IOException, InterruptedException
	{
        for (AdjacentWord value : values)
		{
			if(i >= 100)
				break;
			
			if(key.get() == 1.0)
				continue;
			
			context.write(value, key);
			i++;
        }
    }
}
