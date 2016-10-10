package com.acnebs.posts.irritat0r.domain;

import java.time.temporal.Temporal;

public class Person {
    public Person(final String salutation) {
        this.salutation = salutation;
    }

    private final String salutation;
    private Temporal birthday;

    public String getSalutation() {
        return salutation;
    }

    public Temporal getBirthday() {
        return birthday;
    }


    public static class Builder {

        private final Person person;

        private Builder(final String addressText){
            this.person = new Person(addressText);
        }

        public static Builder of(final String addressText) {
            return new Builder(addressText);
        }

        public Person build() {
            return person;
        }

        public Builder withBirthday(final Temporal value) {
            person.birthday = value;
            return this;
        }
    }
}
