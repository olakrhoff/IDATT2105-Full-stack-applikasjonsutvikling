app.component('display',
{
    props:
    {
        displayValue:
        {
            type: String
        }
    },

    template:
    /*html*/
    `<div>
        <h1>{{ displayValue }}</h1>
    </div>`,

    data()
    {
        return {
            value: '0'
        }
    },

    methods:
    {
    },

    computed:
    {
        
    }
});