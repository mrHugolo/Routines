import { useEffect, useState } from "react/cjs/react.development"

export const Statistics = () => {
  const [routines, setRoutines] = useState([])
  const [daysBack, setDaysBack] = useState(7)

  useEffect( async () => {
    let res = await fetch(`/rest/routineTracker/stat/${daysBack}`)
    setRoutines(await res.json())
  }, [daysBack])

  const handleKeyPress = (e) => {
    if (e.key != "Enter") return
    var reg = /^\d+$/;
    if (reg.test(e.target.value)) setDaysBack(e.target.value)
    
  }

  const isSuccess = (bol, comment) => {
    let OK = (comment[0] == "O" && comment[1] == "K")
    if (bol && !OK) return (
      < button onClick={() => showComment(comment)} className="success small" ><span>{comment && `*`}</span>✓</button >
    )
    else if (OK) return (
      <button onClick={() => showComment(comment.substring(2, comment.length))} className="OK small" >━</button >
    )
    else return (
      <button onClick={() => showComment(comment)} className="failure small"><span>{comment && `*`}</span>✖</button>
    )
  }

  const showComment = (comment) => {
    if(!comment) return
    let e = document.getElementById("daysBackXcomment")
    
    e.style.width = "calc(80vw - 6px)" //border = 3px
    e.style.left = "10%" // 50% - 80vw/2
    e.value = comment
  }

  return(
    <div className="content noselect">
      <div className="center"><input id="daysBackXcomment" onKeyPress={handleKeyPress} defaultValue={daysBack}/></div>
      <div className="statContainer">
        {routines?.length && routines.map((r,i) => (
          <div key={`routineStat-${i}`} className="stat">
            <p>{r.name}</p>
            <div className="flexStat">
              {r.routineTrackerStat && r.routineTrackerStat.map((s, ii) => (
                <div key={`isSuccess-${ii}`}>
                  {isSuccess(s.isSuccess, s.comment)}
                </div>
              ))}
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}