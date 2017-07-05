package com.holmeslei.greendaodemo.database;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.holmeslei.greendaodemo.model.NotePage;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NOTE_PAGE".
*/
public class NotePageDao extends AbstractDao<NotePage, Long> {

    public static final String TABLENAME = "NOTE_PAGE";

    /**
     * Properties of entity NotePage.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property PageIndex = new Property(1, int.class, "pageIndex", false, "PAGE_INDEX");
        public final static Property BookId = new Property(2, long.class, "bookId", false, "BOOK_ID");
    }

    private DaoSession daoSession;

    private Query<NotePage> noteBook_PageListQuery;

    public NotePageDao(DaoConfig config) {
        super(config);
    }
    
    public NotePageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NOTE_PAGE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: id
                "\"PAGE_INDEX\" INTEGER NOT NULL ," + // 1: pageIndex
                "\"BOOK_ID\" INTEGER NOT NULL );"); // 2: bookId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NOTE_PAGE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NotePage entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getPageIndex());
        stmt.bindLong(3, entity.getBookId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NotePage entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getPageIndex());
        stmt.bindLong(3, entity.getBookId());
    }

    @Override
    protected final void attachEntity(NotePage entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public NotePage readEntity(Cursor cursor, int offset) {
        NotePage entity = new NotePage( //
            cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // pageIndex
            cursor.getLong(offset + 2) // bookId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NotePage entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setPageIndex(cursor.getInt(offset + 1));
        entity.setBookId(cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NotePage entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NotePage entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NotePage entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "pageList" to-many relationship of NoteBook. */
    public List<NotePage> _queryNoteBook_PageList(long bookId) {
        synchronized (this) {
            if (noteBook_PageListQuery == null) {
                QueryBuilder<NotePage> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.BookId.eq(null));
                noteBook_PageListQuery = queryBuilder.build();
            }
        }
        Query<NotePage> query = noteBook_PageListQuery.forCurrentThread();
        query.setParameter(0, bookId);
        return query.list();
    }

}
