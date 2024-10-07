package com.example.server.models;

public class IUserRoster {
    private Long id;
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

    public IUserRoster() {
    }

    public IUserRoster(Long id, Long position1, Long position2, Long position3, Long position4, Long position5, Long position6, Long position7, Long position8, Long position9, Long position10, Long position11, Long sub1, Long sub2, Long sub3, Long sub4) {
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

    public IUserRoster(Long position1, Long position2, Long position3, Long position4, Long position5, Long position6, Long position7, Long position8, Long position9, Long position10, Long position11, Long sub1, Long sub2, Long sub3, Long sub4) {
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

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Long getPosition1() {
        return position1;
    }

    public void setPosition1(Long position1) {
        this.position1 = position1;
    }

    public Long getPosition2() {
        return position2;
    }

    public void setPosition2(Long position2) {
        this.position2 = position2;
    }

    public Long getPosition3() {
        return position3;
    }

    public void setPosition3(Long position3) {
        this.position3 = position3;
    }

    public Long getPosition4() {
        return position4;
    }

    public void setPosition4(Long position4) {
        this.position4 = position4;
    }

    public Long getPosition5() {
        return position5;
    }

    public void setPosition5(Long position5) {
        this.position5 = position5;
    }

    public Long getPosition6() {
        return position6;
    }

    public void setPosition6(Long position6) {
        this.position6 = position6;
    }

    public Long getPosition7() {
        return position7;
    }

    public void setPosition7(Long position7) {
        this.position7 = position7;
    }

    public Long getPosition8() {
        return position8;
    }

    public void setPosition8(Long position8) {
        this.position8 = position8;
    }

    public Long getPosition9() {
        return position9;
    }

    public void setPosition9(Long position9) {
        this.position9 = position9;
    }

    public Long getPosition10() {
        return position10;
    }

    public void setPosition10(Long position10) {
        this.position10 = position10;
    }

    public Long getPosition11() {
        return position11;
    }

    public void setPosition11(Long position11) {
        this.position11 = position11;
    }

    public Long getSub1() {
        return sub1;
    }

    public void setSub1(Long sub1) {
        this.sub1 = sub1;
    }

    public Long getSub2() {
        return sub2;
    }

    public void setSub2(Long sub2) {
        this.sub2 = sub2;
    }

    public Long getSub3() {
        return sub3;
    }

    public void setSub3(Long sub3) {
        this.sub3 = sub3;
    }

    public Long getSub4() {
        return sub4;
    }

    public void setSub4(Long sub4) {
        this.sub4 = sub4;
    }
}
