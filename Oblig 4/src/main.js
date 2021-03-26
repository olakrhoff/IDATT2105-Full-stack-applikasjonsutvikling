const app = Vue.createApp(
{
    data()
    {
        return {
            entries: []
        }
    },

    methods:
    {
        addEntry(entry)
        {
            this.entries.push(entry);
        }
    }
});