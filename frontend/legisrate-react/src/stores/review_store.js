import { store } from 'react-easy-state'
 
async function parseJson(response) {
    const jsonData = []
    const rawData = await response.text()
    rawData.split("\n")
        .forEach((jsonString) => {
            if ( (jsonString.length > 0) ) {
                try {
                    jsonData.push(JSON.parse(jsonString))
                } catch (error) {
                    console.error(error)
                }
            }
        })
 
    return jsonData
}
 
const reviewStore = store({
    reviews: [],
    async loadData(jsonUrl) {
        const response = await fetch(jsonUrl)
        const jsonData = await parseJson(response)
        reviewStore.reviews = jsonData
    }
})
 
export default reviewStore