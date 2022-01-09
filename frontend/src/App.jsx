import { useEffect, useState } from "react"

function App() {
  const url = "http://localhost:4000/rest"
  const today = new Date().setUTCHours(0,0,0,0)
  const hour12InMilliSeconds = 43200000

  const [day, setDay] = useState(today)
  const [sections, setSections] = useState([])
  const [routines, setRoutines] = useState([])
  
  useEffect(async () => {
    let sections = await fetch(`${url}/section`)
    setSections(await sections.json())

    fetchRoutinesOfDay()
  }, [])

  const fetchRoutinesOfDay = async (timestamp) => {
    let routineTrackers = await fetch(`${url}/routineTracker/${timestamp ? timestamp : day}`)
    setRoutines(await routineTrackers.json())
  }

  const humanDate = (desc) => {
    const date = new Date(day)
    const days = ["Söndag", "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag"]

    switch(desc) {
      case "date": return date.toLocaleDateString("sv-SV")
      case "weekday": return days[date.getDay()]
    }
  }

  const increaseDay = (i) => {
    const newDay = new Date(day + hour12InMilliSeconds * (1 + 2 * i)).setUTCHours(0, 0, 0, 0)
    setDay(newDay)
    fetchRoutinesOfDay(newDay)
  }

  const handleClick = async (e) => {
    const arr = e.target.id.split('-')
    const success = arr[0] == "success"
    const routineId = arr[1]
    const comment = document.getElementById(`comment-${routineId}`)?.value

    const routineTracker = {
      routineId,
      day,
      success,
      comment
    }

    let res = await fetch(`${url}/routineTracker`, {
      method: 'POST',
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(routineTracker)
    })
    
    if(res.status != 200) return;
    let rs = routines.slice()
    let routine = rs.find(r => r.id == routineId)
    routine.comment = comment
    routine.success = success
    setRoutines(rs)
  }

  return (
    <div className="content noselect">
      <div className="dateFlex">
        <span>{humanDate("weekday")}</span>
        <h1>
          <span className="pointer" onClick={() => increaseDay(-1)}>&#x3c;</span>
          {humanDate("date")}
          <span className="pointer" onClick={() => increaseDay(1)}>&#x3e;</span>
        </h1>
      </div>
      <div className="sectionContainer">
        <div className="lineDivider"></div>
        {sections.map((s, i) => (
          <div key={`section-${i}`} id={`section-${i}`} className="section">
            <h3>{s.name}</h3>
            <div className="routineContainer">
              {routines?.length && routines.filter(r => r.sectionId == s.id).sort((a, b) => a.priority > b.priority ? 1 : -1).map((r, ii) => (
                <div key={`routine-${ii}`} id={`routine-${ii}`} 
                className={`routine ${r.hasOwnProperty("success") ? `${r.success ? `success` : `failure`}` : ``}`}>
                  <p>{r.name}</p>
                  {r.hasOwnProperty("success") ? 
                  (<h6 className="comment">{r.comment}</h6>) : 
                  (<input id={`comment-${r.id}`} placeholder="Kommentar" />)}
                  <div className={r.hasOwnProperty("success") ? `invisible`:``}>
                    <button id={`success-${r.id}`} onClick={(e) => {handleClick(e)}} className="success">✓</button>
                    <button id={`failure-${r.id}`} onClick={(e) => {handleClick(e)}} className="failure">✖</button>
                  </div>
                </div>
              ))}
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default App
