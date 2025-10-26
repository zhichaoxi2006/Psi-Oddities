package com.zhichaoxi.psi_oddities.attachment;

public class PsiWingData {
    private int gracePeriod = 0;
    private boolean enabled = false;

    public PsiWingData setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public PsiWingData setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
        return this;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
