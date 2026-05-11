// ORM class for table 'fact_trips'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon May 11 15:57:56 MSK 2026
// For connector: org.apache.sqoop.manager.PostgresqlManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import org.apache.sqoop.lib.JdbcWritableBridge;
import org.apache.sqoop.lib.DelimiterSet;
import org.apache.sqoop.lib.FieldFormatter;
import org.apache.sqoop.lib.RecordParser;
import org.apache.sqoop.lib.BooleanParser;
import org.apache.sqoop.lib.BlobRef;
import org.apache.sqoop.lib.ClobRef;
import org.apache.sqoop.lib.LargeObjectLoader;
import org.apache.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class fact_trips extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("trip_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.trip_id = (Long)value;
      }
    });
    setters.put("taxi_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.taxi_id = (Integer)value;
      }
    });
    setters.put("company_code", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.company_code = (Integer)value;
      }
    });
    setters.put("payment_type", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.payment_type = (String)value;
      }
    });
    setters.put("trip_start_timestamp", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.trip_start_timestamp = (java.sql.Timestamp)value;
      }
    });
    setters.put("trip_end_timestamp", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.trip_end_timestamp = (java.sql.Timestamp)value;
      }
    });
    setters.put("trip_seconds", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.trip_seconds = (Integer)value;
      }
    });
    setters.put("trip_miles", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.trip_miles = (java.math.BigDecimal)value;
      }
    });
    setters.put("pickup_census_tract", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.pickup_census_tract = (Integer)value;
      }
    });
    setters.put("dropoff_census_tract", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.dropoff_census_tract = (Integer)value;
      }
    });
    setters.put("pickup_community_area", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.pickup_community_area = (Integer)value;
      }
    });
    setters.put("dropoff_community_area", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.dropoff_community_area = (Integer)value;
      }
    });
    setters.put("fare", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.fare = (java.math.BigDecimal)value;
      }
    });
    setters.put("tips", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.tips = (java.math.BigDecimal)value;
      }
    });
    setters.put("tolls", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.tolls = (java.math.BigDecimal)value;
      }
    });
    setters.put("extras", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.extras = (java.math.BigDecimal)value;
      }
    });
    setters.put("trip_total", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.trip_total = (java.math.BigDecimal)value;
      }
    });
    setters.put("pickup_latitude_code", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.pickup_latitude_code = (Integer)value;
      }
    });
    setters.put("pickup_longitude_code", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.pickup_longitude_code = (Integer)value;
      }
    });
    setters.put("dropoff_latitude_code", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.dropoff_latitude_code = (Integer)value;
      }
    });
    setters.put("dropoff_longitude_code", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.dropoff_longitude_code = (Integer)value;
      }
    });
    setters.put("trip_year", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.trip_year = (Integer)value;
      }
    });
    setters.put("trip_month", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.trip_month = (Integer)value;
      }
    });
    setters.put("trip_day", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.trip_day = (Integer)value;
      }
    });
    setters.put("trip_hour", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.trip_hour = (Integer)value;
      }
    });
    setters.put("trip_weekday", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.trip_weekday = (Integer)value;
      }
    });
    setters.put("is_valid_end_timestamp", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.is_valid_end_timestamp = (Boolean)value;
      }
    });
    setters.put("is_valid_duration", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.is_valid_duration = (Boolean)value;
      }
    });
    setters.put("is_valid_amounts", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        fact_trips.this.is_valid_amounts = (Boolean)value;
      }
    });
  }
  public fact_trips() {
    init0();
  }
  private Long trip_id;
  public Long get_trip_id() {
    return trip_id;
  }
  public void set_trip_id(Long trip_id) {
    this.trip_id = trip_id;
  }
  public fact_trips with_trip_id(Long trip_id) {
    this.trip_id = trip_id;
    return this;
  }
  private Integer taxi_id;
  public Integer get_taxi_id() {
    return taxi_id;
  }
  public void set_taxi_id(Integer taxi_id) {
    this.taxi_id = taxi_id;
  }
  public fact_trips with_taxi_id(Integer taxi_id) {
    this.taxi_id = taxi_id;
    return this;
  }
  private Integer company_code;
  public Integer get_company_code() {
    return company_code;
  }
  public void set_company_code(Integer company_code) {
    this.company_code = company_code;
  }
  public fact_trips with_company_code(Integer company_code) {
    this.company_code = company_code;
    return this;
  }
  private String payment_type;
  public String get_payment_type() {
    return payment_type;
  }
  public void set_payment_type(String payment_type) {
    this.payment_type = payment_type;
  }
  public fact_trips with_payment_type(String payment_type) {
    this.payment_type = payment_type;
    return this;
  }
  private java.sql.Timestamp trip_start_timestamp;
  public java.sql.Timestamp get_trip_start_timestamp() {
    return trip_start_timestamp;
  }
  public void set_trip_start_timestamp(java.sql.Timestamp trip_start_timestamp) {
    this.trip_start_timestamp = trip_start_timestamp;
  }
  public fact_trips with_trip_start_timestamp(java.sql.Timestamp trip_start_timestamp) {
    this.trip_start_timestamp = trip_start_timestamp;
    return this;
  }
  private java.sql.Timestamp trip_end_timestamp;
  public java.sql.Timestamp get_trip_end_timestamp() {
    return trip_end_timestamp;
  }
  public void set_trip_end_timestamp(java.sql.Timestamp trip_end_timestamp) {
    this.trip_end_timestamp = trip_end_timestamp;
  }
  public fact_trips with_trip_end_timestamp(java.sql.Timestamp trip_end_timestamp) {
    this.trip_end_timestamp = trip_end_timestamp;
    return this;
  }
  private Integer trip_seconds;
  public Integer get_trip_seconds() {
    return trip_seconds;
  }
  public void set_trip_seconds(Integer trip_seconds) {
    this.trip_seconds = trip_seconds;
  }
  public fact_trips with_trip_seconds(Integer trip_seconds) {
    this.trip_seconds = trip_seconds;
    return this;
  }
  private java.math.BigDecimal trip_miles;
  public java.math.BigDecimal get_trip_miles() {
    return trip_miles;
  }
  public void set_trip_miles(java.math.BigDecimal trip_miles) {
    this.trip_miles = trip_miles;
  }
  public fact_trips with_trip_miles(java.math.BigDecimal trip_miles) {
    this.trip_miles = trip_miles;
    return this;
  }
  private Integer pickup_census_tract;
  public Integer get_pickup_census_tract() {
    return pickup_census_tract;
  }
  public void set_pickup_census_tract(Integer pickup_census_tract) {
    this.pickup_census_tract = pickup_census_tract;
  }
  public fact_trips with_pickup_census_tract(Integer pickup_census_tract) {
    this.pickup_census_tract = pickup_census_tract;
    return this;
  }
  private Integer dropoff_census_tract;
  public Integer get_dropoff_census_tract() {
    return dropoff_census_tract;
  }
  public void set_dropoff_census_tract(Integer dropoff_census_tract) {
    this.dropoff_census_tract = dropoff_census_tract;
  }
  public fact_trips with_dropoff_census_tract(Integer dropoff_census_tract) {
    this.dropoff_census_tract = dropoff_census_tract;
    return this;
  }
  private Integer pickup_community_area;
  public Integer get_pickup_community_area() {
    return pickup_community_area;
  }
  public void set_pickup_community_area(Integer pickup_community_area) {
    this.pickup_community_area = pickup_community_area;
  }
  public fact_trips with_pickup_community_area(Integer pickup_community_area) {
    this.pickup_community_area = pickup_community_area;
    return this;
  }
  private Integer dropoff_community_area;
  public Integer get_dropoff_community_area() {
    return dropoff_community_area;
  }
  public void set_dropoff_community_area(Integer dropoff_community_area) {
    this.dropoff_community_area = dropoff_community_area;
  }
  public fact_trips with_dropoff_community_area(Integer dropoff_community_area) {
    this.dropoff_community_area = dropoff_community_area;
    return this;
  }
  private java.math.BigDecimal fare;
  public java.math.BigDecimal get_fare() {
    return fare;
  }
  public void set_fare(java.math.BigDecimal fare) {
    this.fare = fare;
  }
  public fact_trips with_fare(java.math.BigDecimal fare) {
    this.fare = fare;
    return this;
  }
  private java.math.BigDecimal tips;
  public java.math.BigDecimal get_tips() {
    return tips;
  }
  public void set_tips(java.math.BigDecimal tips) {
    this.tips = tips;
  }
  public fact_trips with_tips(java.math.BigDecimal tips) {
    this.tips = tips;
    return this;
  }
  private java.math.BigDecimal tolls;
  public java.math.BigDecimal get_tolls() {
    return tolls;
  }
  public void set_tolls(java.math.BigDecimal tolls) {
    this.tolls = tolls;
  }
  public fact_trips with_tolls(java.math.BigDecimal tolls) {
    this.tolls = tolls;
    return this;
  }
  private java.math.BigDecimal extras;
  public java.math.BigDecimal get_extras() {
    return extras;
  }
  public void set_extras(java.math.BigDecimal extras) {
    this.extras = extras;
  }
  public fact_trips with_extras(java.math.BigDecimal extras) {
    this.extras = extras;
    return this;
  }
  private java.math.BigDecimal trip_total;
  public java.math.BigDecimal get_trip_total() {
    return trip_total;
  }
  public void set_trip_total(java.math.BigDecimal trip_total) {
    this.trip_total = trip_total;
  }
  public fact_trips with_trip_total(java.math.BigDecimal trip_total) {
    this.trip_total = trip_total;
    return this;
  }
  private Integer pickup_latitude_code;
  public Integer get_pickup_latitude_code() {
    return pickup_latitude_code;
  }
  public void set_pickup_latitude_code(Integer pickup_latitude_code) {
    this.pickup_latitude_code = pickup_latitude_code;
  }
  public fact_trips with_pickup_latitude_code(Integer pickup_latitude_code) {
    this.pickup_latitude_code = pickup_latitude_code;
    return this;
  }
  private Integer pickup_longitude_code;
  public Integer get_pickup_longitude_code() {
    return pickup_longitude_code;
  }
  public void set_pickup_longitude_code(Integer pickup_longitude_code) {
    this.pickup_longitude_code = pickup_longitude_code;
  }
  public fact_trips with_pickup_longitude_code(Integer pickup_longitude_code) {
    this.pickup_longitude_code = pickup_longitude_code;
    return this;
  }
  private Integer dropoff_latitude_code;
  public Integer get_dropoff_latitude_code() {
    return dropoff_latitude_code;
  }
  public void set_dropoff_latitude_code(Integer dropoff_latitude_code) {
    this.dropoff_latitude_code = dropoff_latitude_code;
  }
  public fact_trips with_dropoff_latitude_code(Integer dropoff_latitude_code) {
    this.dropoff_latitude_code = dropoff_latitude_code;
    return this;
  }
  private Integer dropoff_longitude_code;
  public Integer get_dropoff_longitude_code() {
    return dropoff_longitude_code;
  }
  public void set_dropoff_longitude_code(Integer dropoff_longitude_code) {
    this.dropoff_longitude_code = dropoff_longitude_code;
  }
  public fact_trips with_dropoff_longitude_code(Integer dropoff_longitude_code) {
    this.dropoff_longitude_code = dropoff_longitude_code;
    return this;
  }
  private Integer trip_year;
  public Integer get_trip_year() {
    return trip_year;
  }
  public void set_trip_year(Integer trip_year) {
    this.trip_year = trip_year;
  }
  public fact_trips with_trip_year(Integer trip_year) {
    this.trip_year = trip_year;
    return this;
  }
  private Integer trip_month;
  public Integer get_trip_month() {
    return trip_month;
  }
  public void set_trip_month(Integer trip_month) {
    this.trip_month = trip_month;
  }
  public fact_trips with_trip_month(Integer trip_month) {
    this.trip_month = trip_month;
    return this;
  }
  private Integer trip_day;
  public Integer get_trip_day() {
    return trip_day;
  }
  public void set_trip_day(Integer trip_day) {
    this.trip_day = trip_day;
  }
  public fact_trips with_trip_day(Integer trip_day) {
    this.trip_day = trip_day;
    return this;
  }
  private Integer trip_hour;
  public Integer get_trip_hour() {
    return trip_hour;
  }
  public void set_trip_hour(Integer trip_hour) {
    this.trip_hour = trip_hour;
  }
  public fact_trips with_trip_hour(Integer trip_hour) {
    this.trip_hour = trip_hour;
    return this;
  }
  private Integer trip_weekday;
  public Integer get_trip_weekday() {
    return trip_weekday;
  }
  public void set_trip_weekday(Integer trip_weekday) {
    this.trip_weekday = trip_weekday;
  }
  public fact_trips with_trip_weekday(Integer trip_weekday) {
    this.trip_weekday = trip_weekday;
    return this;
  }
  private Boolean is_valid_end_timestamp;
  public Boolean get_is_valid_end_timestamp() {
    return is_valid_end_timestamp;
  }
  public void set_is_valid_end_timestamp(Boolean is_valid_end_timestamp) {
    this.is_valid_end_timestamp = is_valid_end_timestamp;
  }
  public fact_trips with_is_valid_end_timestamp(Boolean is_valid_end_timestamp) {
    this.is_valid_end_timestamp = is_valid_end_timestamp;
    return this;
  }
  private Boolean is_valid_duration;
  public Boolean get_is_valid_duration() {
    return is_valid_duration;
  }
  public void set_is_valid_duration(Boolean is_valid_duration) {
    this.is_valid_duration = is_valid_duration;
  }
  public fact_trips with_is_valid_duration(Boolean is_valid_duration) {
    this.is_valid_duration = is_valid_duration;
    return this;
  }
  private Boolean is_valid_amounts;
  public Boolean get_is_valid_amounts() {
    return is_valid_amounts;
  }
  public void set_is_valid_amounts(Boolean is_valid_amounts) {
    this.is_valid_amounts = is_valid_amounts;
  }
  public fact_trips with_is_valid_amounts(Boolean is_valid_amounts) {
    this.is_valid_amounts = is_valid_amounts;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof fact_trips)) {
      return false;
    }
    fact_trips that = (fact_trips) o;
    boolean equal = true;
    equal = equal && (this.trip_id == null ? that.trip_id == null : this.trip_id.equals(that.trip_id));
    equal = equal && (this.taxi_id == null ? that.taxi_id == null : this.taxi_id.equals(that.taxi_id));
    equal = equal && (this.company_code == null ? that.company_code == null : this.company_code.equals(that.company_code));
    equal = equal && (this.payment_type == null ? that.payment_type == null : this.payment_type.equals(that.payment_type));
    equal = equal && (this.trip_start_timestamp == null ? that.trip_start_timestamp == null : this.trip_start_timestamp.equals(that.trip_start_timestamp));
    equal = equal && (this.trip_end_timestamp == null ? that.trip_end_timestamp == null : this.trip_end_timestamp.equals(that.trip_end_timestamp));
    equal = equal && (this.trip_seconds == null ? that.trip_seconds == null : this.trip_seconds.equals(that.trip_seconds));
    equal = equal && (this.trip_miles == null ? that.trip_miles == null : this.trip_miles.equals(that.trip_miles));
    equal = equal && (this.pickup_census_tract == null ? that.pickup_census_tract == null : this.pickup_census_tract.equals(that.pickup_census_tract));
    equal = equal && (this.dropoff_census_tract == null ? that.dropoff_census_tract == null : this.dropoff_census_tract.equals(that.dropoff_census_tract));
    equal = equal && (this.pickup_community_area == null ? that.pickup_community_area == null : this.pickup_community_area.equals(that.pickup_community_area));
    equal = equal && (this.dropoff_community_area == null ? that.dropoff_community_area == null : this.dropoff_community_area.equals(that.dropoff_community_area));
    equal = equal && (this.fare == null ? that.fare == null : this.fare.equals(that.fare));
    equal = equal && (this.tips == null ? that.tips == null : this.tips.equals(that.tips));
    equal = equal && (this.tolls == null ? that.tolls == null : this.tolls.equals(that.tolls));
    equal = equal && (this.extras == null ? that.extras == null : this.extras.equals(that.extras));
    equal = equal && (this.trip_total == null ? that.trip_total == null : this.trip_total.equals(that.trip_total));
    equal = equal && (this.pickup_latitude_code == null ? that.pickup_latitude_code == null : this.pickup_latitude_code.equals(that.pickup_latitude_code));
    equal = equal && (this.pickup_longitude_code == null ? that.pickup_longitude_code == null : this.pickup_longitude_code.equals(that.pickup_longitude_code));
    equal = equal && (this.dropoff_latitude_code == null ? that.dropoff_latitude_code == null : this.dropoff_latitude_code.equals(that.dropoff_latitude_code));
    equal = equal && (this.dropoff_longitude_code == null ? that.dropoff_longitude_code == null : this.dropoff_longitude_code.equals(that.dropoff_longitude_code));
    equal = equal && (this.trip_year == null ? that.trip_year == null : this.trip_year.equals(that.trip_year));
    equal = equal && (this.trip_month == null ? that.trip_month == null : this.trip_month.equals(that.trip_month));
    equal = equal && (this.trip_day == null ? that.trip_day == null : this.trip_day.equals(that.trip_day));
    equal = equal && (this.trip_hour == null ? that.trip_hour == null : this.trip_hour.equals(that.trip_hour));
    equal = equal && (this.trip_weekday == null ? that.trip_weekday == null : this.trip_weekday.equals(that.trip_weekday));
    equal = equal && (this.is_valid_end_timestamp == null ? that.is_valid_end_timestamp == null : this.is_valid_end_timestamp.equals(that.is_valid_end_timestamp));
    equal = equal && (this.is_valid_duration == null ? that.is_valid_duration == null : this.is_valid_duration.equals(that.is_valid_duration));
    equal = equal && (this.is_valid_amounts == null ? that.is_valid_amounts == null : this.is_valid_amounts.equals(that.is_valid_amounts));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof fact_trips)) {
      return false;
    }
    fact_trips that = (fact_trips) o;
    boolean equal = true;
    equal = equal && (this.trip_id == null ? that.trip_id == null : this.trip_id.equals(that.trip_id));
    equal = equal && (this.taxi_id == null ? that.taxi_id == null : this.taxi_id.equals(that.taxi_id));
    equal = equal && (this.company_code == null ? that.company_code == null : this.company_code.equals(that.company_code));
    equal = equal && (this.payment_type == null ? that.payment_type == null : this.payment_type.equals(that.payment_type));
    equal = equal && (this.trip_start_timestamp == null ? that.trip_start_timestamp == null : this.trip_start_timestamp.equals(that.trip_start_timestamp));
    equal = equal && (this.trip_end_timestamp == null ? that.trip_end_timestamp == null : this.trip_end_timestamp.equals(that.trip_end_timestamp));
    equal = equal && (this.trip_seconds == null ? that.trip_seconds == null : this.trip_seconds.equals(that.trip_seconds));
    equal = equal && (this.trip_miles == null ? that.trip_miles == null : this.trip_miles.equals(that.trip_miles));
    equal = equal && (this.pickup_census_tract == null ? that.pickup_census_tract == null : this.pickup_census_tract.equals(that.pickup_census_tract));
    equal = equal && (this.dropoff_census_tract == null ? that.dropoff_census_tract == null : this.dropoff_census_tract.equals(that.dropoff_census_tract));
    equal = equal && (this.pickup_community_area == null ? that.pickup_community_area == null : this.pickup_community_area.equals(that.pickup_community_area));
    equal = equal && (this.dropoff_community_area == null ? that.dropoff_community_area == null : this.dropoff_community_area.equals(that.dropoff_community_area));
    equal = equal && (this.fare == null ? that.fare == null : this.fare.equals(that.fare));
    equal = equal && (this.tips == null ? that.tips == null : this.tips.equals(that.tips));
    equal = equal && (this.tolls == null ? that.tolls == null : this.tolls.equals(that.tolls));
    equal = equal && (this.extras == null ? that.extras == null : this.extras.equals(that.extras));
    equal = equal && (this.trip_total == null ? that.trip_total == null : this.trip_total.equals(that.trip_total));
    equal = equal && (this.pickup_latitude_code == null ? that.pickup_latitude_code == null : this.pickup_latitude_code.equals(that.pickup_latitude_code));
    equal = equal && (this.pickup_longitude_code == null ? that.pickup_longitude_code == null : this.pickup_longitude_code.equals(that.pickup_longitude_code));
    equal = equal && (this.dropoff_latitude_code == null ? that.dropoff_latitude_code == null : this.dropoff_latitude_code.equals(that.dropoff_latitude_code));
    equal = equal && (this.dropoff_longitude_code == null ? that.dropoff_longitude_code == null : this.dropoff_longitude_code.equals(that.dropoff_longitude_code));
    equal = equal && (this.trip_year == null ? that.trip_year == null : this.trip_year.equals(that.trip_year));
    equal = equal && (this.trip_month == null ? that.trip_month == null : this.trip_month.equals(that.trip_month));
    equal = equal && (this.trip_day == null ? that.trip_day == null : this.trip_day.equals(that.trip_day));
    equal = equal && (this.trip_hour == null ? that.trip_hour == null : this.trip_hour.equals(that.trip_hour));
    equal = equal && (this.trip_weekday == null ? that.trip_weekday == null : this.trip_weekday.equals(that.trip_weekday));
    equal = equal && (this.is_valid_end_timestamp == null ? that.is_valid_end_timestamp == null : this.is_valid_end_timestamp.equals(that.is_valid_end_timestamp));
    equal = equal && (this.is_valid_duration == null ? that.is_valid_duration == null : this.is_valid_duration.equals(that.is_valid_duration));
    equal = equal && (this.is_valid_amounts == null ? that.is_valid_amounts == null : this.is_valid_amounts.equals(that.is_valid_amounts));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.trip_id = JdbcWritableBridge.readLong(1, __dbResults);
    this.taxi_id = JdbcWritableBridge.readInteger(2, __dbResults);
    this.company_code = JdbcWritableBridge.readInteger(3, __dbResults);
    this.payment_type = JdbcWritableBridge.readString(4, __dbResults);
    this.trip_start_timestamp = JdbcWritableBridge.readTimestamp(5, __dbResults);
    this.trip_end_timestamp = JdbcWritableBridge.readTimestamp(6, __dbResults);
    this.trip_seconds = JdbcWritableBridge.readInteger(7, __dbResults);
    this.trip_miles = JdbcWritableBridge.readBigDecimal(8, __dbResults);
    this.pickup_census_tract = JdbcWritableBridge.readInteger(9, __dbResults);
    this.dropoff_census_tract = JdbcWritableBridge.readInteger(10, __dbResults);
    this.pickup_community_area = JdbcWritableBridge.readInteger(11, __dbResults);
    this.dropoff_community_area = JdbcWritableBridge.readInteger(12, __dbResults);
    this.fare = JdbcWritableBridge.readBigDecimal(13, __dbResults);
    this.tips = JdbcWritableBridge.readBigDecimal(14, __dbResults);
    this.tolls = JdbcWritableBridge.readBigDecimal(15, __dbResults);
    this.extras = JdbcWritableBridge.readBigDecimal(16, __dbResults);
    this.trip_total = JdbcWritableBridge.readBigDecimal(17, __dbResults);
    this.pickup_latitude_code = JdbcWritableBridge.readInteger(18, __dbResults);
    this.pickup_longitude_code = JdbcWritableBridge.readInteger(19, __dbResults);
    this.dropoff_latitude_code = JdbcWritableBridge.readInteger(20, __dbResults);
    this.dropoff_longitude_code = JdbcWritableBridge.readInteger(21, __dbResults);
    this.trip_year = JdbcWritableBridge.readInteger(22, __dbResults);
    this.trip_month = JdbcWritableBridge.readInteger(23, __dbResults);
    this.trip_day = JdbcWritableBridge.readInteger(24, __dbResults);
    this.trip_hour = JdbcWritableBridge.readInteger(25, __dbResults);
    this.trip_weekday = JdbcWritableBridge.readInteger(26, __dbResults);
    this.is_valid_end_timestamp = JdbcWritableBridge.readBoolean(27, __dbResults);
    this.is_valid_duration = JdbcWritableBridge.readBoolean(28, __dbResults);
    this.is_valid_amounts = JdbcWritableBridge.readBoolean(29, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.trip_id = JdbcWritableBridge.readLong(1, __dbResults);
    this.taxi_id = JdbcWritableBridge.readInteger(2, __dbResults);
    this.company_code = JdbcWritableBridge.readInteger(3, __dbResults);
    this.payment_type = JdbcWritableBridge.readString(4, __dbResults);
    this.trip_start_timestamp = JdbcWritableBridge.readTimestamp(5, __dbResults);
    this.trip_end_timestamp = JdbcWritableBridge.readTimestamp(6, __dbResults);
    this.trip_seconds = JdbcWritableBridge.readInteger(7, __dbResults);
    this.trip_miles = JdbcWritableBridge.readBigDecimal(8, __dbResults);
    this.pickup_census_tract = JdbcWritableBridge.readInteger(9, __dbResults);
    this.dropoff_census_tract = JdbcWritableBridge.readInteger(10, __dbResults);
    this.pickup_community_area = JdbcWritableBridge.readInteger(11, __dbResults);
    this.dropoff_community_area = JdbcWritableBridge.readInteger(12, __dbResults);
    this.fare = JdbcWritableBridge.readBigDecimal(13, __dbResults);
    this.tips = JdbcWritableBridge.readBigDecimal(14, __dbResults);
    this.tolls = JdbcWritableBridge.readBigDecimal(15, __dbResults);
    this.extras = JdbcWritableBridge.readBigDecimal(16, __dbResults);
    this.trip_total = JdbcWritableBridge.readBigDecimal(17, __dbResults);
    this.pickup_latitude_code = JdbcWritableBridge.readInteger(18, __dbResults);
    this.pickup_longitude_code = JdbcWritableBridge.readInteger(19, __dbResults);
    this.dropoff_latitude_code = JdbcWritableBridge.readInteger(20, __dbResults);
    this.dropoff_longitude_code = JdbcWritableBridge.readInteger(21, __dbResults);
    this.trip_year = JdbcWritableBridge.readInteger(22, __dbResults);
    this.trip_month = JdbcWritableBridge.readInteger(23, __dbResults);
    this.trip_day = JdbcWritableBridge.readInteger(24, __dbResults);
    this.trip_hour = JdbcWritableBridge.readInteger(25, __dbResults);
    this.trip_weekday = JdbcWritableBridge.readInteger(26, __dbResults);
    this.is_valid_end_timestamp = JdbcWritableBridge.readBoolean(27, __dbResults);
    this.is_valid_duration = JdbcWritableBridge.readBoolean(28, __dbResults);
    this.is_valid_amounts = JdbcWritableBridge.readBoolean(29, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(trip_id, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(taxi_id, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(company_code, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(payment_type, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(trip_start_timestamp, 5 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(trip_end_timestamp, 6 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(trip_seconds, 7 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(trip_miles, 8 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeInteger(pickup_census_tract, 9 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(dropoff_census_tract, 10 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(pickup_community_area, 11 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(dropoff_community_area, 12 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(fare, 13 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(tips, 14 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(tolls, 15 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(extras, 16 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(trip_total, 17 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeInteger(pickup_latitude_code, 18 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(pickup_longitude_code, 19 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(dropoff_latitude_code, 20 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(dropoff_longitude_code, 21 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(trip_year, 22 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(trip_month, 23 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(trip_day, 24 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(trip_hour, 25 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(trip_weekday, 26 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeBoolean(is_valid_end_timestamp, 27 + __off, -7, __dbStmt);
    JdbcWritableBridge.writeBoolean(is_valid_duration, 28 + __off, -7, __dbStmt);
    JdbcWritableBridge.writeBoolean(is_valid_amounts, 29 + __off, -7, __dbStmt);
    return 29;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(trip_id, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(taxi_id, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(company_code, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(payment_type, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(trip_start_timestamp, 5 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(trip_end_timestamp, 6 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(trip_seconds, 7 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(trip_miles, 8 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeInteger(pickup_census_tract, 9 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(dropoff_census_tract, 10 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(pickup_community_area, 11 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(dropoff_community_area, 12 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(fare, 13 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(tips, 14 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(tolls, 15 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(extras, 16 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(trip_total, 17 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeInteger(pickup_latitude_code, 18 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(pickup_longitude_code, 19 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(dropoff_latitude_code, 20 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(dropoff_longitude_code, 21 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(trip_year, 22 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(trip_month, 23 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(trip_day, 24 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(trip_hour, 25 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(trip_weekday, 26 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeBoolean(is_valid_end_timestamp, 27 + __off, -7, __dbStmt);
    JdbcWritableBridge.writeBoolean(is_valid_duration, 28 + __off, -7, __dbStmt);
    JdbcWritableBridge.writeBoolean(is_valid_amounts, 29 + __off, -7, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.trip_id = null;
    } else {
    this.trip_id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.taxi_id = null;
    } else {
    this.taxi_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.company_code = null;
    } else {
    this.company_code = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.payment_type = null;
    } else {
    this.payment_type = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.trip_start_timestamp = null;
    } else {
    this.trip_start_timestamp = new Timestamp(__dataIn.readLong());
    this.trip_start_timestamp.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.trip_end_timestamp = null;
    } else {
    this.trip_end_timestamp = new Timestamp(__dataIn.readLong());
    this.trip_end_timestamp.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.trip_seconds = null;
    } else {
    this.trip_seconds = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.trip_miles = null;
    } else {
    this.trip_miles = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.pickup_census_tract = null;
    } else {
    this.pickup_census_tract = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.dropoff_census_tract = null;
    } else {
    this.dropoff_census_tract = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.pickup_community_area = null;
    } else {
    this.pickup_community_area = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.dropoff_community_area = null;
    } else {
    this.dropoff_community_area = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.fare = null;
    } else {
    this.fare = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.tips = null;
    } else {
    this.tips = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.tolls = null;
    } else {
    this.tolls = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.extras = null;
    } else {
    this.extras = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.trip_total = null;
    } else {
    this.trip_total = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.pickup_latitude_code = null;
    } else {
    this.pickup_latitude_code = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.pickup_longitude_code = null;
    } else {
    this.pickup_longitude_code = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.dropoff_latitude_code = null;
    } else {
    this.dropoff_latitude_code = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.dropoff_longitude_code = null;
    } else {
    this.dropoff_longitude_code = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.trip_year = null;
    } else {
    this.trip_year = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.trip_month = null;
    } else {
    this.trip_month = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.trip_day = null;
    } else {
    this.trip_day = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.trip_hour = null;
    } else {
    this.trip_hour = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.trip_weekday = null;
    } else {
    this.trip_weekday = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.is_valid_end_timestamp = null;
    } else {
    this.is_valid_end_timestamp = Boolean.valueOf(__dataIn.readBoolean());
    }
    if (__dataIn.readBoolean()) { 
        this.is_valid_duration = null;
    } else {
    this.is_valid_duration = Boolean.valueOf(__dataIn.readBoolean());
    }
    if (__dataIn.readBoolean()) { 
        this.is_valid_amounts = null;
    } else {
    this.is_valid_amounts = Boolean.valueOf(__dataIn.readBoolean());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.trip_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.trip_id);
    }
    if (null == this.taxi_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.taxi_id);
    }
    if (null == this.company_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.company_code);
    }
    if (null == this.payment_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, payment_type);
    }
    if (null == this.trip_start_timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.trip_start_timestamp.getTime());
    __dataOut.writeInt(this.trip_start_timestamp.getNanos());
    }
    if (null == this.trip_end_timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.trip_end_timestamp.getTime());
    __dataOut.writeInt(this.trip_end_timestamp.getNanos());
    }
    if (null == this.trip_seconds) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.trip_seconds);
    }
    if (null == this.trip_miles) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.trip_miles, __dataOut);
    }
    if (null == this.pickup_census_tract) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.pickup_census_tract);
    }
    if (null == this.dropoff_census_tract) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.dropoff_census_tract);
    }
    if (null == this.pickup_community_area) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.pickup_community_area);
    }
    if (null == this.dropoff_community_area) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.dropoff_community_area);
    }
    if (null == this.fare) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.fare, __dataOut);
    }
    if (null == this.tips) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.tips, __dataOut);
    }
    if (null == this.tolls) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.tolls, __dataOut);
    }
    if (null == this.extras) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.extras, __dataOut);
    }
    if (null == this.trip_total) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.trip_total, __dataOut);
    }
    if (null == this.pickup_latitude_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.pickup_latitude_code);
    }
    if (null == this.pickup_longitude_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.pickup_longitude_code);
    }
    if (null == this.dropoff_latitude_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.dropoff_latitude_code);
    }
    if (null == this.dropoff_longitude_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.dropoff_longitude_code);
    }
    if (null == this.trip_year) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.trip_year);
    }
    if (null == this.trip_month) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.trip_month);
    }
    if (null == this.trip_day) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.trip_day);
    }
    if (null == this.trip_hour) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.trip_hour);
    }
    if (null == this.trip_weekday) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.trip_weekday);
    }
    if (null == this.is_valid_end_timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeBoolean(this.is_valid_end_timestamp);
    }
    if (null == this.is_valid_duration) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeBoolean(this.is_valid_duration);
    }
    if (null == this.is_valid_amounts) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeBoolean(this.is_valid_amounts);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.trip_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.trip_id);
    }
    if (null == this.taxi_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.taxi_id);
    }
    if (null == this.company_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.company_code);
    }
    if (null == this.payment_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, payment_type);
    }
    if (null == this.trip_start_timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.trip_start_timestamp.getTime());
    __dataOut.writeInt(this.trip_start_timestamp.getNanos());
    }
    if (null == this.trip_end_timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.trip_end_timestamp.getTime());
    __dataOut.writeInt(this.trip_end_timestamp.getNanos());
    }
    if (null == this.trip_seconds) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.trip_seconds);
    }
    if (null == this.trip_miles) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.trip_miles, __dataOut);
    }
    if (null == this.pickup_census_tract) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.pickup_census_tract);
    }
    if (null == this.dropoff_census_tract) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.dropoff_census_tract);
    }
    if (null == this.pickup_community_area) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.pickup_community_area);
    }
    if (null == this.dropoff_community_area) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.dropoff_community_area);
    }
    if (null == this.fare) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.fare, __dataOut);
    }
    if (null == this.tips) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.tips, __dataOut);
    }
    if (null == this.tolls) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.tolls, __dataOut);
    }
    if (null == this.extras) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.extras, __dataOut);
    }
    if (null == this.trip_total) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.trip_total, __dataOut);
    }
    if (null == this.pickup_latitude_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.pickup_latitude_code);
    }
    if (null == this.pickup_longitude_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.pickup_longitude_code);
    }
    if (null == this.dropoff_latitude_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.dropoff_latitude_code);
    }
    if (null == this.dropoff_longitude_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.dropoff_longitude_code);
    }
    if (null == this.trip_year) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.trip_year);
    }
    if (null == this.trip_month) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.trip_month);
    }
    if (null == this.trip_day) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.trip_day);
    }
    if (null == this.trip_hour) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.trip_hour);
    }
    if (null == this.trip_weekday) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.trip_weekday);
    }
    if (null == this.is_valid_end_timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeBoolean(this.is_valid_end_timestamp);
    }
    if (null == this.is_valid_duration) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeBoolean(this.is_valid_duration);
    }
    if (null == this.is_valid_amounts) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeBoolean(this.is_valid_amounts);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(trip_id==null?"null":"" + trip_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(taxi_id==null?"null":"" + taxi_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(company_code==null?"null":"" + company_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(payment_type==null?"null":payment_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_start_timestamp==null?"null":"" + trip_start_timestamp, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_end_timestamp==null?"null":"" + trip_end_timestamp, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_seconds==null?"null":"" + trip_seconds, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_miles==null?"null":trip_miles.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(pickup_census_tract==null?"null":"" + pickup_census_tract, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dropoff_census_tract==null?"null":"" + dropoff_census_tract, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(pickup_community_area==null?"null":"" + pickup_community_area, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dropoff_community_area==null?"null":"" + dropoff_community_area, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(fare==null?"null":fare.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tips==null?"null":tips.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tolls==null?"null":tolls.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(extras==null?"null":extras.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_total==null?"null":trip_total.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(pickup_latitude_code==null?"null":"" + pickup_latitude_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(pickup_longitude_code==null?"null":"" + pickup_longitude_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dropoff_latitude_code==null?"null":"" + dropoff_latitude_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dropoff_longitude_code==null?"null":"" + dropoff_longitude_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_year==null?"null":"" + trip_year, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_month==null?"null":"" + trip_month, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_day==null?"null":"" + trip_day, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_hour==null?"null":"" + trip_hour, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_weekday==null?"null":"" + trip_weekday, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_valid_end_timestamp==null?"null":"" + is_valid_end_timestamp, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_valid_duration==null?"null":"" + is_valid_duration, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_valid_amounts==null?"null":"" + is_valid_amounts, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(trip_id==null?"null":"" + trip_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(taxi_id==null?"null":"" + taxi_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(company_code==null?"null":"" + company_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(payment_type==null?"null":payment_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_start_timestamp==null?"null":"" + trip_start_timestamp, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_end_timestamp==null?"null":"" + trip_end_timestamp, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_seconds==null?"null":"" + trip_seconds, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_miles==null?"null":trip_miles.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(pickup_census_tract==null?"null":"" + pickup_census_tract, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dropoff_census_tract==null?"null":"" + dropoff_census_tract, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(pickup_community_area==null?"null":"" + pickup_community_area, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dropoff_community_area==null?"null":"" + dropoff_community_area, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(fare==null?"null":fare.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tips==null?"null":tips.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tolls==null?"null":tolls.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(extras==null?"null":extras.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_total==null?"null":trip_total.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(pickup_latitude_code==null?"null":"" + pickup_latitude_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(pickup_longitude_code==null?"null":"" + pickup_longitude_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dropoff_latitude_code==null?"null":"" + dropoff_latitude_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dropoff_longitude_code==null?"null":"" + dropoff_longitude_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_year==null?"null":"" + trip_year, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_month==null?"null":"" + trip_month, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_day==null?"null":"" + trip_day, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_hour==null?"null":"" + trip_hour, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trip_weekday==null?"null":"" + trip_weekday, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_valid_end_timestamp==null?"null":"" + is_valid_end_timestamp, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_valid_duration==null?"null":"" + is_valid_duration, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(is_valid_amounts==null?"null":"" + is_valid_amounts, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_id = null; } else {
      this.trip_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.taxi_id = null; } else {
      this.taxi_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.company_code = null; } else {
      this.company_code = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.payment_type = null; } else {
      this.payment_type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_start_timestamp = null; } else {
      this.trip_start_timestamp = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_end_timestamp = null; } else {
      this.trip_end_timestamp = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_seconds = null; } else {
      this.trip_seconds = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_miles = null; } else {
      this.trip_miles = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.pickup_census_tract = null; } else {
      this.pickup_census_tract = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dropoff_census_tract = null; } else {
      this.dropoff_census_tract = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.pickup_community_area = null; } else {
      this.pickup_community_area = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dropoff_community_area = null; } else {
      this.dropoff_community_area = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.fare = null; } else {
      this.fare = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.tips = null; } else {
      this.tips = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.tolls = null; } else {
      this.tolls = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.extras = null; } else {
      this.extras = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_total = null; } else {
      this.trip_total = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.pickup_latitude_code = null; } else {
      this.pickup_latitude_code = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.pickup_longitude_code = null; } else {
      this.pickup_longitude_code = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dropoff_latitude_code = null; } else {
      this.dropoff_latitude_code = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dropoff_longitude_code = null; } else {
      this.dropoff_longitude_code = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_year = null; } else {
      this.trip_year = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_month = null; } else {
      this.trip_month = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_day = null; } else {
      this.trip_day = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_hour = null; } else {
      this.trip_hour = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_weekday = null; } else {
      this.trip_weekday = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.is_valid_end_timestamp = null; } else {
      this.is_valid_end_timestamp = BooleanParser.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.is_valid_duration = null; } else {
      this.is_valid_duration = BooleanParser.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.is_valid_amounts = null; } else {
      this.is_valid_amounts = BooleanParser.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_id = null; } else {
      this.trip_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.taxi_id = null; } else {
      this.taxi_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.company_code = null; } else {
      this.company_code = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.payment_type = null; } else {
      this.payment_type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_start_timestamp = null; } else {
      this.trip_start_timestamp = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_end_timestamp = null; } else {
      this.trip_end_timestamp = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_seconds = null; } else {
      this.trip_seconds = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_miles = null; } else {
      this.trip_miles = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.pickup_census_tract = null; } else {
      this.pickup_census_tract = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dropoff_census_tract = null; } else {
      this.dropoff_census_tract = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.pickup_community_area = null; } else {
      this.pickup_community_area = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dropoff_community_area = null; } else {
      this.dropoff_community_area = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.fare = null; } else {
      this.fare = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.tips = null; } else {
      this.tips = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.tolls = null; } else {
      this.tolls = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.extras = null; } else {
      this.extras = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_total = null; } else {
      this.trip_total = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.pickup_latitude_code = null; } else {
      this.pickup_latitude_code = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.pickup_longitude_code = null; } else {
      this.pickup_longitude_code = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dropoff_latitude_code = null; } else {
      this.dropoff_latitude_code = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dropoff_longitude_code = null; } else {
      this.dropoff_longitude_code = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_year = null; } else {
      this.trip_year = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_month = null; } else {
      this.trip_month = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_day = null; } else {
      this.trip_day = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_hour = null; } else {
      this.trip_hour = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.trip_weekday = null; } else {
      this.trip_weekday = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.is_valid_end_timestamp = null; } else {
      this.is_valid_end_timestamp = BooleanParser.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.is_valid_duration = null; } else {
      this.is_valid_duration = BooleanParser.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.is_valid_amounts = null; } else {
      this.is_valid_amounts = BooleanParser.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    fact_trips o = (fact_trips) super.clone();
    o.trip_start_timestamp = (o.trip_start_timestamp != null) ? (java.sql.Timestamp) o.trip_start_timestamp.clone() : null;
    o.trip_end_timestamp = (o.trip_end_timestamp != null) ? (java.sql.Timestamp) o.trip_end_timestamp.clone() : null;
    return o;
  }

  public void clone0(fact_trips o) throws CloneNotSupportedException {
    o.trip_start_timestamp = (o.trip_start_timestamp != null) ? (java.sql.Timestamp) o.trip_start_timestamp.clone() : null;
    o.trip_end_timestamp = (o.trip_end_timestamp != null) ? (java.sql.Timestamp) o.trip_end_timestamp.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("trip_id", this.trip_id);
    __sqoop$field_map.put("taxi_id", this.taxi_id);
    __sqoop$field_map.put("company_code", this.company_code);
    __sqoop$field_map.put("payment_type", this.payment_type);
    __sqoop$field_map.put("trip_start_timestamp", this.trip_start_timestamp);
    __sqoop$field_map.put("trip_end_timestamp", this.trip_end_timestamp);
    __sqoop$field_map.put("trip_seconds", this.trip_seconds);
    __sqoop$field_map.put("trip_miles", this.trip_miles);
    __sqoop$field_map.put("pickup_census_tract", this.pickup_census_tract);
    __sqoop$field_map.put("dropoff_census_tract", this.dropoff_census_tract);
    __sqoop$field_map.put("pickup_community_area", this.pickup_community_area);
    __sqoop$field_map.put("dropoff_community_area", this.dropoff_community_area);
    __sqoop$field_map.put("fare", this.fare);
    __sqoop$field_map.put("tips", this.tips);
    __sqoop$field_map.put("tolls", this.tolls);
    __sqoop$field_map.put("extras", this.extras);
    __sqoop$field_map.put("trip_total", this.trip_total);
    __sqoop$field_map.put("pickup_latitude_code", this.pickup_latitude_code);
    __sqoop$field_map.put("pickup_longitude_code", this.pickup_longitude_code);
    __sqoop$field_map.put("dropoff_latitude_code", this.dropoff_latitude_code);
    __sqoop$field_map.put("dropoff_longitude_code", this.dropoff_longitude_code);
    __sqoop$field_map.put("trip_year", this.trip_year);
    __sqoop$field_map.put("trip_month", this.trip_month);
    __sqoop$field_map.put("trip_day", this.trip_day);
    __sqoop$field_map.put("trip_hour", this.trip_hour);
    __sqoop$field_map.put("trip_weekday", this.trip_weekday);
    __sqoop$field_map.put("is_valid_end_timestamp", this.is_valid_end_timestamp);
    __sqoop$field_map.put("is_valid_duration", this.is_valid_duration);
    __sqoop$field_map.put("is_valid_amounts", this.is_valid_amounts);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("trip_id", this.trip_id);
    __sqoop$field_map.put("taxi_id", this.taxi_id);
    __sqoop$field_map.put("company_code", this.company_code);
    __sqoop$field_map.put("payment_type", this.payment_type);
    __sqoop$field_map.put("trip_start_timestamp", this.trip_start_timestamp);
    __sqoop$field_map.put("trip_end_timestamp", this.trip_end_timestamp);
    __sqoop$field_map.put("trip_seconds", this.trip_seconds);
    __sqoop$field_map.put("trip_miles", this.trip_miles);
    __sqoop$field_map.put("pickup_census_tract", this.pickup_census_tract);
    __sqoop$field_map.put("dropoff_census_tract", this.dropoff_census_tract);
    __sqoop$field_map.put("pickup_community_area", this.pickup_community_area);
    __sqoop$field_map.put("dropoff_community_area", this.dropoff_community_area);
    __sqoop$field_map.put("fare", this.fare);
    __sqoop$field_map.put("tips", this.tips);
    __sqoop$field_map.put("tolls", this.tolls);
    __sqoop$field_map.put("extras", this.extras);
    __sqoop$field_map.put("trip_total", this.trip_total);
    __sqoop$field_map.put("pickup_latitude_code", this.pickup_latitude_code);
    __sqoop$field_map.put("pickup_longitude_code", this.pickup_longitude_code);
    __sqoop$field_map.put("dropoff_latitude_code", this.dropoff_latitude_code);
    __sqoop$field_map.put("dropoff_longitude_code", this.dropoff_longitude_code);
    __sqoop$field_map.put("trip_year", this.trip_year);
    __sqoop$field_map.put("trip_month", this.trip_month);
    __sqoop$field_map.put("trip_day", this.trip_day);
    __sqoop$field_map.put("trip_hour", this.trip_hour);
    __sqoop$field_map.put("trip_weekday", this.trip_weekday);
    __sqoop$field_map.put("is_valid_end_timestamp", this.is_valid_end_timestamp);
    __sqoop$field_map.put("is_valid_duration", this.is_valid_duration);
    __sqoop$field_map.put("is_valid_amounts", this.is_valid_amounts);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
