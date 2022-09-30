package com.codewars.chrisgw.author;

import com.codewars.chrisgw.author.HandwrittenAutographScanner.LetterClass;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;


public class Name implements Comparable<Name> {

    private final String name;
    private final List<LetterClass> decodedLetterClass;

    public Name(String name) {
        this.name = name;
        this.decodedLetterClass = new ArrayList<>(name.length());
        for (int i = 0; i < length(); i++) {
            LetterClass letterClass = LetterClass.valueOf(charAt(i));
            decodedLetterClass.add(letterClass);
        }
    }


    public boolean match(String name) {
        return match(new Name(name));
    }

    public boolean match(Name name) {
        boolean wrongLength = length() != name.length();
        if (wrongLength || charAt(0) != name.charAt(0)) {
            return false;
        }
        for (int i = 1; i < length(); i++) {
            LetterClass letterClass = name.letterAt(i);
            LetterClass readLetterClass = letterAt(i);
            if (!letterClass.equals(readLetterClass)) {
                return false;
            }
        }
        return true;
    }


    public char charAt(int i) {
        return name.charAt(i);
    }

    public char firstChar() {
        return charAt(0);
    }


    public int length() {
        return name.length();
    }


    public LetterClass letterAt(int i) {
        return decodedLetterClass.get(i);
    }


    @Override
    public int compareTo(Name otherName) {
        return this.name.compareTo(otherName.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }

        Name name = (Name) o;
        return new EqualsBuilder().append(decodedLetterClass, name.decodedLetterClass).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(decodedLetterClass).toHashCode();
    }

    @Override
    public String toString() {
        return name;
    }

}
