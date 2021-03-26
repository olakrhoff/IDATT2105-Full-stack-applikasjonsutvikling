app.component('buttons',
{
    template:
    /*html*/
    `<div class="buttons">
        <div class="buttonRow" v-for="row in buttonRows">
            <div
            @click="buttonClicked(button.text)"
            :class="{ operatorButton: button.type == 'operator' }"
            class="button"
            v-for="button in row">
            {{ button.text }}
            </div>
        </div>
    </div>`,

    data()
    {
        return {
            buttonRows:
            [
                [{text: 'C', type: 'operator'}, {text: 'ANS', type: 'operator'}, {text: 'DEL', type: 'operator'}, {text: '+', type: 'operator'}],
                [{text: '7', type: 'number'}, {text: '8', type: 'number'}, {text: '9', type: 'number'}, {text: '-', type: 'operator'}],
                [{text: '4', type: 'number'}, {text: '5', type: 'number'}, {text: '6', type: 'number'}, {text: 'x', type: 'operator'}],
                [{text: '1', type: 'number'}, {text: '2', type: 'number'}, {text: '3', type: 'number'}, {text: '/', type: 'operator'}],
                [{text: '0', type: 'number'}, {text: '.', type: 'number'}, {text: '=', type: 'operator'}]
            ]
        }
    },

    methods:
    {
        buttonClicked(text)
        {
            this.$emit('button-clicked', text);
        }
    }
})




/*`<div class="grid-container-buttons">
<button-display id="button0"></button-display>
<button-display id="button1"></button-display>
<button-display id="button2"></button-display>
<button-display id="button3"></button-display>
<button-display id="button4"></button-display>
<button-display id="button5"></button-display>
<button-display id="button6"></button-display>
<button-display id="button7"></button-display>
<button-display id="button8"></button-display>
<button-display id="button9"></button-display>
<button-display id="buttonC" class="specialOperator"></button-display>
<button-display id="buttonANS" class="specialOperator"></button-display>
<button-display id="buttonDEL" class="specialOperator"></button-display>
<button-display id="buttonPLUSS" class="operator"></button-display>
<button-display id="buttonMINUS" class="operator"></button-display>
<button-display id="buttonTIMES" class="operator"></button-display>
<button-display id="buttonDIVIDE" class="operator"></button-display>
<button-display id="buttonEQUALS" class="operator"></button-display>
<button-display id="buttonDOT"></button-display>
</div>` */