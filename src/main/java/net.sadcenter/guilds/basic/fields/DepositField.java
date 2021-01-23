package net.sadcenter.guilds.basic.fields;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author sadcenter on 27.08.2020
 * @project LASTCORE
 */
@Data
@AllArgsConstructor
public final class DepositField {

    private int koxs;
    private int refils;
    private int pearls;
    private int arrows;
    private int ice;
    private int snows;
    private int eggs;
    private int packedIce;

    public void removeKox(int index) {
        koxs -= index;
    }

    public void removeRefil(int index) {
        refils -= index;
    }

    public void removePearls(int index) {
        pearls -= index;
    }

    public void removeArrows(int index) {
        arrows -= index;
    }

    public void removeIce(int index) {
        ice -= index;
    }

    public void removeSnows(int index) {
        snows -= index;
    }

    public void removeEggs(int index) {
        eggs -= index;
    }

    public void removePackedIce(int index) {
        packedIce -= index;
    }

    //

    public void addKox(int index) {
        koxs += index;
    }

    public void addRefil(int index) {
        refils += index;
    }

    public void addPearls(int index) {
        pearls += index;
    }

    public void addArrows(int index) {
        arrows += index;
    }

    public void addIce(int index) {
        ice += index;
    }

    public void addSnows(int index) {
        snows += index;
    }

    public void addEggs(int index) {
        eggs += index;
    }

    public void addPackedIce(int index) {
        packedIce += index;
    }
}
