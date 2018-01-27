import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.*;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class Descender extends WritableComparator
{
    protected Descender()
	{
        super(DoubleWritable.class, true);
    }
	
    @SuppressWarnings("rawtypes")
    @Override
    public int compare(WritableComparable first, WritableComparable second)
	{
        DoubleWritable one = (DoubleWritable) first;
        DoubleWritable two = (DoubleWritable) second;          
        return one.compareTo(two) * -1;
    }
}

