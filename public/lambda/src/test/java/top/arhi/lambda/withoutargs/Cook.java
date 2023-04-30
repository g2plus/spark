package top.arhi.lambda.withoutargs;

/**
 * @author e2607
 * @version 1.0
 * @description: TODO
 * @date 11/29/2021 12:42 PM
 */
public class Cook {

    private Ingredient ingredient;

    public Cook() {
    }

    public Cook(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void start(){
        ingredient.cook();
    }
}
