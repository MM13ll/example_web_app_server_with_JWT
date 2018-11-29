package uz.course.to;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class Select2DataSource implements Serializable {
    @JsonProperty
    private ArrayList<Item> results = new ArrayList<>(20);

    public void add(Item item) {
        results.add(item);
    }

    public Select2DataSource add(Long id, String text) {
        add(new Item(id, text));
        return this;
    }

    @JsonProperty
    public Pagination getPagination() {
        if (results.isEmpty())
            return new Pagination(false);
        else
            return new Pagination(true);
    }

    public class Item implements Serializable {
        @JsonProperty
        private Long id;
        @JsonProperty
        private String text;

        public Item() {
        }

        public Item(Long id, String text) {
            this.id = id;
            this.text = text;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public class Pagination implements Serializable {
        @JsonProperty
        private boolean more = false;

        public Pagination(boolean more) {
            this.more = more;
        }

        public boolean isMore() {
            return more;
        }

        public void setMore(boolean more) {
            this.more = more;
        }
    }
}