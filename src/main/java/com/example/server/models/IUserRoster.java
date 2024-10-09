package com.example.server.models;

/**
 * Interface for the user roster.
 */
public class IUserRoster {
    private Long id;
    private double price;
    private double score;
    private Long position1;
    private Long position2;
    private Long position3;
    private Long position4;
    private Long position5;
    private Long position6;
    private Long position7;
    private Long position8;
    private Long position9;
    private Long position10;
    private Long position11;
    private Long sub1;
    private Long sub2;
    private Long sub3;
    private Long sub4;

    /**
     * Create a new IUserRoster instance.
     */
    public IUserRoster() {
        this.price = 0.0;
        this.score = 0.0;
    }

    /**
     * Create a new IUserRoster instance.
     * 
     * @param id         The ID
     * @param position1  The ID of the player in position 1
     * @param position2  The ID of the player in position 2
     * @param position3  The ID of the player in position 3
     * @param position4  The ID of the player in position 4
     * @param position5  The ID of the player in position 5
     * @param position6  The ID of the player in position 6
     * @param position7  The ID of the player in position 7
     * @param position8  The ID of the player in position 8
     * @param position9  The ID of the player in position 9
     * @param position10 The ID of the player in position 10
     * @param position11 The ID of the player in position 11
     * @param sub1       The ID of the player in the first substitute position
     * @param sub2       The ID of the player in the second substitute position
     * @param sub3       The ID of the player in the third substitute position
     * @param sub4       The ID of the player in the fourth substitute position
     */
    public IUserRoster(Long id, Long position1, Long position2, Long position3, Long position4, Long position5,
            Long position6, Long position7, Long position8, Long position9, Long position10, Long position11, Long sub1,
            Long sub2, Long sub3, Long sub4) {
        this.id = id;
        this.position1 = position1;
        this.position2 = position2;
        this.position3 = position3;
        this.position4 = position4;
        this.position5 = position5;
        this.position6 = position6;
        this.position7 = position7;
        this.position8 = position8;
        this.position9 = position9;
        this.position10 = position10;
        this.position11 = position11;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
        this.sub4 = sub4;
    }

    /**
     * Create a new IUserRoster instance.
     * 
     * @param position1  The ID of the player in position 1
     * @param position2  The ID of the player in position 2
     * @param position3  The ID of the player in position 3
     * @param position4  The ID of the player in position 4
     * @param position5  The ID of the player in position 5
     * @param position6  The ID of the player in position 6
     * @param position7  The ID of the player in position 7
     * @param position8  The ID of the player in position 8
     * @param position9  The ID of the player in position 9
     * @param position10 The ID of the player in position 10
     * @param position11 The ID of the player in position 11
     * @param sub1       The ID of the player in the first substitute position
     * @param sub2       The ID of the player in the second substitute position
     * @param sub3       The ID of the player in the third substitute position
     * @param sub4       The ID of the player in the fourth substitute position
     */
    public IUserRoster(Long position1, Long position2, Long position3, Long position4, Long position5, Long position6,
            Long position7, Long position8, Long position9, Long position10, Long position11, Long sub1, Long sub2,
            Long sub3, Long sub4) {
        this.position1 = position1;
        this.position2 = position2;
        this.position3 = position3;
        this.position4 = position4;
        this.position5 = position5;
        this.position6 = position6;
        this.position7 = position7;
        this.position8 = position8;
        this.position9 = position9;
        this.position10 = position10;
        this.position11 = position11;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
        this.sub4 = sub4;
    }

    // Methods
    /**
     * Increase the price of the roster.
     * 
     * @param price The price to add
     * @return The new price
     */
    public double incPrice(double price) {
        double _price = this.price + price;
        this.price = Math.round(_price * 10.0) / 10.0;
        return this.price;
    }

    /**
     * Decrease the price of the roster.
     * 
     * @param price The price to subtract
     * @return The new price
     */
    public double decPrice(double price) {
        double _price = this.price - price;
        this.price = Math.round(_price * 10.0) / 10.0;
        return this.price;
    }

    /**
     * Increase the score of the roster.
     * 
     * @param score The score to add
     * @return The new score
     */
    public double incScore(double score) {
        this.score += score;
        return this.score;
    }

    /**
     * Decrease the score of the roster.
     * 
     * @param score The score to subtract
     * @return The new score
     */
    public double decScore(double score) {
        this.score -= score;
        return this.score;
    }

    // Getters
    /**
     * Get the ID of the roster.
     * 
     * @return The ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the price of the roster.
     * 
     * @return The price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the score of the roster.
     * 
     * @return The score
     */
    public double getScore() {
        return score;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in position 1
     */
    public Long getPosition1() {
        return position1;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in position 2
     */
    public Long getPosition2() {
        return position2;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in position 3
     */
    public Long getPosition3() {
        return position3;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in position 4
     */
    public Long getPosition4() {
        return position4;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in position 5
     */
    public Long getPosition5() {
        return position5;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in position 6
     */
    public Long getPosition6() {
        return position6;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in position 7
     */
    public Long getPosition7() {
        return position7;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in position 8
     */
    public Long getPosition8() {
        return position8;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in position 9
     */
    public Long getPosition9() {
        return position9;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in position 10
     */
    public Long getPosition10() {
        return position10;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in position 11
     */
    public Long getPosition11() {
        return position11;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in the first substitute position
     */
    public Long getSub1() {
        return sub1;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in the second substitute position
     */
    public Long getSub2() {
        return sub2;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in the third substitute position
     */
    public Long getSub3() {
        return sub3;
    }

    /**
     * Get the position of the roster.
     * 
     * @return The ID of the player in the fourth substitute position
     */
    public Long getSub4() {
        return sub4;
    }

    // Setters
    /**
     * Set the ID of the roster.
     * 
     * @param id The roster ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Set the price of the roster.
     * 
     * @param price The total roster price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Set the score of the roster.
     * 
     * @param score The total roster score
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Set the position of the roster.
     * 
     * @param position1 The ID of the player in position 1
     */
    public void setPosition1(Long position1) {
        this.position1 = position1;
    }

    /**
     * Set the position of the roster.
     * 
     * @param position2 The ID of the player in position 2
     */
    public void setPosition2(Long position2) {
        this.position2 = position2;
    }

    /**
     * Set the position of the roster.
     * 
     * @param position3 The ID of the player in position 3
     */
    public void setPosition3(Long position3) {
        this.position3 = position3;
    }

    /**
     * Set the position of the roster.
     * 
     * @param position4 The ID of the player in position 4
     */
    public void setPosition4(Long position4) {
        this.position4 = position4;
    }

    /**
     * Set the position of the roster.
     * 
     * @param position5 The ID of the player in position 5
     */
    public void setPosition5(Long position5) {
        this.position5 = position5;
    }

    /**
     * Set the position of the roster.
     * 
     * @param position6 The ID of the player in position 6
     */
    public void setPosition6(Long position6) {
        this.position6 = position6;
    }

    /**
     * Set the position of the roster.
     * 
     * @param position7 The ID of the player in position 7
     */
    public void setPosition7(Long position7) {
        this.position7 = position7;
    }

    /**
     * Set the position of the roster.
     * 
     * @param position8 The ID of the player in position 8
     */
    public void setPosition8(Long position8) {
        this.position8 = position8;
    }

    /**
     * Set the position of the roster.
     * 
     * @param position9 The ID of the player in position 9
     */
    public void setPosition9(Long position9) {
        this.position9 = position9;
    }

    /**
     * Set the position of the roster.
     * 
     * @param position10 The ID of the player in position 10
     */
    public void setPosition10(Long position10) {
        this.position10 = position10;
    }

    /**
     * Set the position of the roster.
     * 
     * @param position11 The ID of the player in position 11
     */
    public void setPosition11(Long position11) {
        this.position11 = position11;
    }

    /**
     * Set the position of the roster.
     * 
     * @param sub1 The ID of the player in the first substitute position
     */
    public void setSub1(Long sub1) {
        this.sub1 = sub1;
    }

    /**
     * Set the position of the roster.
     * 
     * @param sub2 The ID of the player in the second substitute position
     */
    public void setSub2(Long sub2) {
        this.sub2 = sub2;
    }

    /**
     * Set the position of the roster.
     * 
     * @param sub3 The ID of the player in the third substitute position
     */
    public void setSub3(Long sub3) {
        this.sub3 = sub3;
    }

    /**
     * Set the position of the roster.
     * 
     * @param sub4 The ID of the player in the fourth substitute position
     */
    public void setSub4(Long sub4) {
        this.sub4 = sub4;
    }
}
