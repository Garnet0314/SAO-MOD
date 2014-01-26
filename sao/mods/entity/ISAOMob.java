package sao.mods.entity;

public interface ISAOMob
{
    float getSize();

    /*
     * 武器の種類による防御力（割合防御、％）
     */
    int getSlashResist();
    int getThrustResist();
    int getStrikeResist();
    int getPenetrateResist();

    /*
     * 属性による防御力（割合防御、％）
     */
    int getQuakeResist();
    int getWaterResist();
    int getFireResist();
    int getAeroResist();
    int getShineResist();
    int getDarkResist();

    /*
     * 武器の種類による攻撃力（対象に最も効果的なものが選択される）
     */
    float getSlashAttack();
    float getThrustAttack();
    float getStrikeAttack();
    float getPenetrateAttack();

    /*
     * 属性による攻撃力（全て加算）
     */
    float getQuakeAttack();
    float getWaterAttack();
    float getFireAttack();
    float getAeroAttack();
    float getShineAttack();
    float getDarkAttack();

    /*
     * ソードスキルAI用
     */
    boolean shouldSwordSkill();
    boolean continueSwordSkill();
    void readySwordSkill();
    void startSwordSkill();
    void resetSwordSkill();
    boolean doSwordSkill(int par1);
}