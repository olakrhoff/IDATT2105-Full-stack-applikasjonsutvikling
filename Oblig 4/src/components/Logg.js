app.component('logg',
{
    props:
    {
        entries:
        {
          type: Array,
          required: true
        }
    },

    template:
    /*html*/
    `<div class="logg">
        <h1>Logg</h1>
        <ul>
            <li
            class="logg-entry"
            v-for="(entry, index) in entries"
            :key="index"
            >{{ entry.firstNumber }} {{ entry.operator }} {{ entry.secondNumber }} = {{ entry.answer }}</li>
        </ul>
    </div>`
});