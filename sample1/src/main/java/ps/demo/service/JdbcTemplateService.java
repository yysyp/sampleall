package ps.demo.service;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Service;
import ps.demo.dto.MyBook;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

@Slf4j
@Service
public class JdbcTemplateService {

    @Qualifier("sndJdbcTemplate")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Long createTable(MyBook myBook) {
        String title = myBook.getTitle();
        String url = myBook.getUrl();
        String myText = myBook.getMyText();
        byte[] myByte = myBook.getMyByte();
        String createTable = "create table if not exists bookmarks" +
                "(" +
                "id         bigint PRIMARY KEY AUTO_INCREMENT," +
                "title      varchar   not null," +
                "url        varchar   not null," +
                "mytext        text," +
                "mybyte        longblob," +
                "created_at timestamp," +
                "primary key (id)" +
                ");";
        jdbcTemplate.execute(createTable);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            String sql = "insert into bookmarks(title, url, mytext, mybyte, created_at) values(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setString(1, title);
            ps.setString(2, url);
            ps.setString(3, myText);
            ps.setBytes(4, myByte);
            ps.setTimestamp(5, Timestamp.from(Instant.now()));
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public MyBook findById(Long id) {
        final LobHandler lobHandler=new DefaultLobHandler();
        return jdbcTemplate.queryForObject("select * from bookmarks where id=?",
                new RowMapper<MyBook>() {
                    @Override
                    public MyBook mapRow(ResultSet rs, int rowNum) throws SQLException {
                        MyBook myBook = new MyBook();
                        myBook.setId(rs.getLong("id"));
                        myBook.setTitle(rs.getString("title"));
                        myBook.setUrl(rs.getString("url"));
                        myBook.setMyText(rs.getString("mytext"));
                        myBook.setMyByte(lobHandler.getBlobAsBytes(rs, "mybyte"));
                        return myBook;
                    }
                }, id);
    }

    public int updateById(MyBook myBook) {
        return jdbcTemplate.update(conn -> {
            String sql = "update bookmarks set title=?, url=?, mytext=?, mybyte=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, myBook.getTitle());
            ps.setString(2, myBook.getUrl());
            ps.setString(3, myBook.getMyText());
            ps.setBytes(4, myBook.getMyByte());
            ps.setLong(5, myBook.getId());
            return ps;
        });
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from bookmarks where id=?", id);
    }

}
