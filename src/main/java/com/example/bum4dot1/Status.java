package com.example.bum4dot1;

public class Status {
    String id;
    String created_at;
    String in_reply_to_id;
    String in_reply_to_account_id;
    boolean sensitive;
    String spoiler_text;
    String visibility;
    String language;
    String uri;
    String content;
    int replies_count;
    int reblogs_count;
    int favourites_count;
    String edited_at;
    Boolean favourited;
    Boolean reblogged;
    Boolean muted;
    Boolean bookmarked;
    Status reblog;
    Account account;

    @Override
    public String toString() {
        return "Status{" +
                "id='" + id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", content='" + content + '\'' +
                ", reblog=" + reblog +
                ", account=" + account +
                '}';
    }

    public class Account{
        String id;
        String username;
        String acct;
        String display_name;
        Boolean locked;
        Boolean bot;
        Boolean discoverable;

        @Override
        public String toString() {
            return "Account{" +
                    "id='" + id + '\'' +
                    ", username='" + username + '\'' +
                    ", acct='" + acct + '\'' +
                    ", display_name='" + display_name + '\'' +
                    ", locked=" + locked +
                    ", bot=" + bot +
                    ", discoverable=" + discoverable +
                    '}';
        }
    }
}
